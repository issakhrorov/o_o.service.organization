package o_o.service.organization.controller.vacancy

import o_o.service.organization.model.vacancy.Vacancy
import o_o.service.organization.model.vacancy.dto.VacancyInput
import o_o.service.organization.service.vacancy.IVacancyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/vacancies")
class VacancyController(
  val service: IVacancyService
) {
  @PostMapping("/")
  fun createUpdate(@RequestBody input: VacancyInput, principal: Principal): Vacancy {
    return service.createUpdate(input, principal)
  }

  @PostMapping("/activation/{id}")
  fun activateVacancy(@PathVariable id: Long, principal: Principal): Boolean {
    return service.activate(id, principal)
  }
}