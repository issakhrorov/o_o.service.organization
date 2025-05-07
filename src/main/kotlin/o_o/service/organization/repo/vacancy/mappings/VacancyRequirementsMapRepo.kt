package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyRequiredLanguagesMap
import o_o.service.organization.model.vacancy.mappings.VacancyRequirementsMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.*
import org.springframework.stereotype.Repository

@Repository
interface VacancyRequirementsMapRepo: KRepository<VacancyRequirementsMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyRequirementsMap>
}