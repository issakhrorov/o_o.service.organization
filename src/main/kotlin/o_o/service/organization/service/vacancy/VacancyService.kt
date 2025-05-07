package o_o.service.organization.service.vacancy

import jakarta.servlet.Filter
import o_o.service.organization.base.service.BaseCRUDService
import o_o.service.organization.model.EducationType
import o_o.service.organization.model.ExperienceRange
import o_o.service.organization.model.JobShift
import o_o.service.organization.model.VacancyStatus
import o_o.service.organization.model.vacancy.dto.VacancyInput
import o_o.service.organization.model.vacancy.*
import o_o.service.organization.repo.vacancy.VacancyRepo
import o_o.service.organization.service.vacancy.mappings.VacancyMappingService
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.Date

@Service
class VacancyService(
  val mainRepo: VacancyRepo,
  val sqlClient: KSqlClient,
  val vacancyMappingsService: VacancyMappingService,
): IVacancyService, BaseCRUDService<Vacancy>() {
  init {
    this._repository = mainRepo
    this._className = Vacancy::class.java.simpleName
  }

  override fun createUpdate(input: VacancyInput, principal: Principal): Vacancy {
    val entity = Vacancy {
      title = input.title
      description = input.description
      organizationId = input.organization_id
      salaryFrom = input.salary_from
      salaryTo = input.salary_to
      experienceRange = ExperienceRange.fromCode(input.experience_range).code
      requiredEducationType = EducationType.fromCode(input.required_education_type).code
      jobShift = JobShift.fromCode(input.job_shift).code
      workingHours = input.working_hours
      validUntil = input.valid_until

      input.id?.let {
        id = it
        vacancyMappingsService.createMappings(it, input)
      }
    }

    return sqlClient.save(entity).modifiedEntity
  }

  override fun activate(id: Long, principal: Principal): Boolean {

    sqlClient.createUpdate(Vacancy::class) {
      set(table.modified, Date())
      set(table.modifiedBy, principal.name)
      set(table.status, VacancyStatus.ACTIVE.code)

      where (table.id eq id)
    }

    return true
  }

}