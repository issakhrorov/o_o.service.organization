package o_o.service.organization.service.vacancy

import o_o.service.organization.model.vacancy.Vacancy
import o_o.service.organization.model.vacancy.dto.VacancyInput
import o_o.service.organization.base.service.IBaseCRUDService
import java.security.Principal

interface IVacancyService: IBaseCRUDService<Vacancy> {
  fun createUpdate(input: VacancyInput, principal: Principal): Vacancy
  fun activate(id: Long, principal: Principal): Boolean
}