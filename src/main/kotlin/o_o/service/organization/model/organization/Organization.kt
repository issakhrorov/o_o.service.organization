package o_o.service.organization.model.organization

import o_o.service.organization.base.model.BaseModel
import org.babyfish.jimmer.sql.Column
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.OneToMany

@Entity
interface Organization: BaseModel {
  val status: Int
  val accountId: Long

  @OneToMany(mappedBy = "organization")
  // categories may be used here or another entity for organization activities will be created
  val activityTypes: List<OrganizationActivityTypeMap>

  @ManyToOne
  val links: Links?

  val title: String?
  val location: String?

  @Column
  val description: String? // rich text field

  val logo: String?
  val countryId: Long?

  // might be changed to long type if entity for current field will be created
  val embeddedLandingPage: String?
}