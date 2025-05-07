package o_o.service.organization.model.vacancy.mappings

import org.babyfish.jimmer.sql.*

@Entity
interface VacancyRequiredLanguagesMap: BaseVacancyMap {
  val languageId: Long
}