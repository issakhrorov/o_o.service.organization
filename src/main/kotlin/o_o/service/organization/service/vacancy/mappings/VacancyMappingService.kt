package o_o.service.organization.service.vacancy.mappings

import o_o.service.organization.model.vacancy.Vacancy
import o_o.service.organization.model.vacancy.dto.VacancyInput
import o_o.service.organization.model.vacancy.mappings.*
import o_o.service.organization.repo.vacancy.VacancyRepo
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.stereotype.Service

@Service
class VacancyMappingService(
  val sqlClient: KSqlClient,
  val vacancyRepo: VacancyRepo,
) {
  fun createMappings(
    vacancyId: Long,
    vacancyInput: VacancyInput
  ) {
    val vacancy = vacancyRepo.findById(vacancyId).get()
    deleteRemovedMappings(vacancy, vacancyInput)
    createVacancyMappings(vacancy, vacancyInput)
  }

  private fun createVacancyMappings(vacancy: Vacancy, vacancyInput: VacancyInput) {
    val mappings = listOf(
      vacancyInput.skill_ids.map {
        VacancySkillMap {
          vacancyId = vacancy.id
          skillId = it
        }
      },
      vacancyInput.responsibility_list.map {
        VacancyResponsibilityMap {
          vacancyId = vacancy.id
          responsibility = it
        }
      },
      vacancyInput.required_language_ids.map {
        VacancyRequiredLanguagesMap {
          vacancyId = vacancy.id
          languageId = it
        }
      },
      vacancyInput.requirements_list.map {
        VacancyRequirementsMap {
          vacancyId = vacancy.id
          requirement = it
        }
      },
      vacancyInput.privileges_list.map {
        VacancyPrivilegesMap {
          vacancyId = vacancy.id
          privilege = it
        }
      },
      vacancyInput.job_types.map {
        VacancyJobTypesMap {
          vacancyId = vacancy.id
          jobType = it
        }
      },
    )

    mappings.forEach {
      sqlClient.saveEntities(it).items
    }
  }

  private fun deleteRemovedMappings(vacancy: Vacancy, vacancyInput: VacancyInput) {
    val skillsToDelete = vacancy.skills
      .filter { it.skillId !in vacancyInput.skill_ids }
      .map { it.id }
    sqlClient.deleteByIds(VacancySkillMap::class, skillsToDelete)

    val responsibilitiesToDelete = vacancy.responsibilities
      .filter { it.responsibility !in vacancyInput.responsibility_list }
      .map { it.id }
    sqlClient.deleteByIds(VacancyResponsibilityMap::class, responsibilitiesToDelete)

    val requiredLanguageToDelete = vacancy.requiredLanguages
      .filter { it.languageId !in vacancyInput.required_language_ids }
      .map { it.id }
    sqlClient.deleteByIds(VacancyRequiredLanguagesMap::class, requiredLanguageToDelete)

    val requirementsToDelete = vacancy.requirements
      .filter { it.requirement !in vacancyInput.requirements_list }
      .map { it.id }
    sqlClient.deleteByIds(VacancyRequirementsMap::class, requirementsToDelete)

    val privilegeToDelete = vacancy.privileges
      .filter { it.privilege !in vacancyInput.privileges_list }
      .map { it.id }
    sqlClient.deleteByIds(VacancyPrivilegesMap::class, privilegeToDelete)

    val jobTypesToDelete = vacancy.jobTypes
      .filter { it.jobType !in vacancyInput.job_types }
      .map { it.id }
    sqlClient.deleteByIds(VacancyJobTypesMap::class, jobTypesToDelete)
  }
}