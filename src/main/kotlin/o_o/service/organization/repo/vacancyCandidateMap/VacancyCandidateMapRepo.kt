package o_o.service.organization.repo.vacancyCandidateMap

import o_o.service.organization.model.vacancyCandidateMap.VacancyCandidateMap
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyCandidateMapRepo: KRepository<VacancyCandidateMap, Long> {

}