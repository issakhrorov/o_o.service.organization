package o_o.service.organization.model.following

import o_o.service.organization.base.model.BaseModel
import org.babyfish.jimmer.sql.Entity

@Entity
interface Following: BaseModel {
  val userId: Long
  val profileId: Long
}