package o_o.service.organization.model.organization

import org.babyfish.jimmer.sql.*

@Entity
interface OrganizationActivityTypeMap {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  val id: Long

  @ManyToOne
  val organization: Organization

  val activityId: Long
}