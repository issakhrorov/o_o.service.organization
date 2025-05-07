package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyJobTypesMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyJobTypesMapRepo: KRepository<VacancyJobTypesMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyJobTypesMap>
}