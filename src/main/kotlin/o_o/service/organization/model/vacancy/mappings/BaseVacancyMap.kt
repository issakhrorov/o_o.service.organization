package o_o.service.organization.model.vacancy.mappings

import o_o.service.organization.model.vacancy.Vacancy
import org.babyfish.jimmer.sql.*

@MappedSuperclass
interface BaseVacancyMap {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  val id: Long

  @ManyToOne
  val vacancy: Vacancy
}