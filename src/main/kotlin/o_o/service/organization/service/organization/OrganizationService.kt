package o_o.service.organization.service.organization

import o_o.service.organization.base.service.BaseCRUDService
import o_o.service.organization.exception.CustomBadRequestException
import o_o.service.organization.exception.CustomEntryNotFoundException
import o_o.service.organization.exception.CustomForbiddenException
import o_o.service.organization.exception.LoggedError
import o_o.service.organization.external.service.AccountService
import o_o.service.organization.model.ProfileStatus
import o_o.service.organization.model.organization.Links
import o_o.service.organization.model.organization.Organization
import o_o.service.organization.model.organization.OrganizationActivityTypeMap
import o_o.service.organization.model.organization.dto.OrganizationInput
import o_o.service.organization.model.vacancy.*
import o_o.service.organization.model.vacancy.mappings.VacancyJobTypesMap
import o_o.service.organization.repo.organization.OrganizationRepo
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*

@Service
class OrganizationService(
  val mainRepo: OrganizationRepo,
  val accountService: AccountService,
  val sqlClient: KSqlClient,
): IOrganizationService, BaseCRUDService<Organization>() {
  init {
    this._repository = mainRepo
    this._className = Organization::class.simpleName!!
  }

  override fun createUpdate(input: OrganizationInput, principal: Principal): Organization {
    val links = sqlClient.findById(Links::class, input.links_id)
    val entity = Organization {
      accountId = input.account_id
      this.links = links
      title = input.title
      location = input.location
      description = input.description
      logo = input.logo
      countryId = input.country_id
      embeddedLandingPage = input.embedded_landing_page
      input.id?.let {
        id = it
        createOrganizationActivityTypesMap(it, input.activity_type_ids)
      }
    }

    val account = accountService.getById(input.account_id)
    if (input.activate == true) activate(entity, principal)

    return entity
  }

  override fun activate(entity: Organization, principal: Principal): Boolean {
    validateFields(entity)

    changeStatus(entity.id, ProfileStatus.ACTIVE, principal)
    return true
  }

  override fun changeStatus(id: Long, status: ProfileStatus, principal: Principal): Boolean {
    sqlClient.createUpdate(Vacancy::class) {
      set(table.modified, Date())
      set(table.modifiedBy, principal.name)
      set(table.status, status.code)

      where (table.id eq id)
    }

    return true
  }

  override fun getByAccount(accountId: Long, getUnconditionally: Boolean): Organization {
    val res = mainRepo.findByAccountIdAndDeletedFalse(accountId)
      ?: throw LoggedError(CustomBadRequestException("Profile not found!"))

    if (res.status != ProfileStatus.ACTIVE.code) {
      if (getUnconditionally) return res
      throw LoggedError(CustomForbiddenException("Profile is not active!"))
    }

    return res
  }

  override fun getByAccountNullable(accountId: Long, getUnconditionally: Boolean): Organization? {
    val res = mainRepo.findByAccountIdAndDeletedFalse(accountId)
      ?: return null

    if (res.status != ProfileStatus.ACTIVE.code) {
      if (getUnconditionally) return res
      throw LoggedError(CustomForbiddenException("Profile is not active!"))
    }

    return res
  }

  override fun getById(id: Long, getUnconditionally: Boolean): Organization {
    val res = getActiveById(id)

    if (res.status != ProfileStatus.ACTIVE.code) {
      if (getUnconditionally) return res
      throw LoggedError(CustomForbiddenException("Profile is not active!"))
    }

    return res
  }

  override fun getAll(getUnconditionally: Boolean, pageable: Pageable): Page<Organization> {
    return if (getUnconditionally) mainRepo.findAllByDeletedFalse(pageable)
    else mainRepo.findAllByStatusAndDeletedFalse(ProfileStatus.ACTIVE.code, pageable)
  }

  private fun createOrganizationActivityTypesMap(
    organizationId: Long,
    activityIds: List<Long>
  ) {
    val organization = sqlClient.findById(Organization::class, organizationId)
      ?: throw LoggedError(CustomEntryNotFoundException("Organization with id #$organizationId not found"))
    val jobTypesToDelete = organization.activityTypes
      .filter { it.activityId !in activityIds }
      .map { it.id }
    sqlClient.deleteByIds(VacancyJobTypesMap::class, jobTypesToDelete)

    val mapping = activityIds.map {
      OrganizationActivityTypeMap {
        this.organizationId = organizationId
        this.activityId = it
      }
    }

    sqlClient.saveEntities(mapping)
  }
}