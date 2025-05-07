package o_o.service.organization.service.organization

import o_o.service.organization.base.service.IBaseCRUDService
import o_o.service.organization.model.ProfileStatus
import o_o.service.organization.model.organization.Organization
import o_o.service.organization.model.organization.dto.OrganizationInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.security.Principal

interface IOrganizationService: IBaseCRUDService<Organization> {
  fun createUpdate(input: OrganizationInput, principal: Principal): Organization
  fun activate(entity: Organization, principal: Principal): Boolean
  fun changeStatus(id: Long, status: ProfileStatus, principal: Principal): Boolean
  fun getByAccount(accountId: Long, getUnconditionally: Boolean): Organization
  fun getByAccountNullable(accountId: Long, getUnconditionally: Boolean): Organization?
  fun getById(id: Long, getUnconditionally: Boolean): Organization
  fun getAll(getUnconditionally: Boolean, pageable: Pageable): Page<Organization>
}