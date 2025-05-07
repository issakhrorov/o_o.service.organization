package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyRequiredLanguagesMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyRequiredLanguagesMapRepo: KRepository<VacancyRequiredLanguagesMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyRequiredLanguagesMap>
}