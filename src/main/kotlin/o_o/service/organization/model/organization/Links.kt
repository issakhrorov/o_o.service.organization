package o_o.service.organization.model.organization

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id

@Entity
interface Links {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  val id: Long

  val email: String?
  val linkedIn: String?
  val website: String?
  val twitter: String?
  val facebook: String?
  val youtube: String?
  val phone: String?
}