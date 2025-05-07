package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyMatchingResumesMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository


@Repository
interface VacancyMatchingResumesMapRepo: KRepository<VacancyMatchingResumesMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyMatchingResumesMap>
}