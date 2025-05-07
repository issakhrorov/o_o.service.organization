package o_o.service.organization.repo.vacancy

import o_o.service.organization.model.vacancy.Vacancy
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

@Repository
interface VacancyRepo: KRepository<Vacancy, Long> {
}