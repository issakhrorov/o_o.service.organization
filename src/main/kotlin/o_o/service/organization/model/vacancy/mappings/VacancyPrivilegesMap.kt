package o_o.service.organization.model.vacancy.mappings

import org.babyfish.jimmer.sql.*

@Entity
interface VacancyPrivilegesMap: BaseVacancyMap {
  val privilege: String
}
