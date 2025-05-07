package o_o.service.organization.repo.vacancy.mappings

import o_o.service.organization.model.vacancy.mappings.VacancySkillMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancySkillMapRepo: KRepository<VacancySkillMap, Long> {
  fun findAllByVacancyId(vacancyId: Long): List<VacancySkillMap>
}











