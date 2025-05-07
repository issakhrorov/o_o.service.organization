package o_o.service.organization.model.vacancyCandidateMap

import o_o.service.organization.base.model.BaseModel
import org.babyfish.jimmer.sql.*

@Entity
interface VacancyCandidateMap: BaseModel {
  val vacancyId: Long
  val candidateId: Long
}