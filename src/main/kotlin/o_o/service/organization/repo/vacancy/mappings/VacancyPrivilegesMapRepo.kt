package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancyPrivilegesMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyPrivilegesMapRepo: KRepository<VacancyPrivilegesMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancyPrivilegesMap>
}