package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyResponsibilityMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyResponsibilityMapRepo: KRepository<VacancyResponsibilityMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyResponsibilityMap>
}