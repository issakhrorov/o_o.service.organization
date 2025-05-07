package o_o.service.organization.dto.organization

import o_o.service.organization.base.dto.LabelValueDTO
import o_o.service.organization.base.media.MediaDTO
import o_o.service.organization.external.dto.AccountDTO
import o_o.service.organization.model.organization.Organization

data class OrganizationCreateUpdateDTO(
  val id: Long?,
  val activate: Boolean?,
  val account_id: Long,
  val links_id: Long,
  val activity_type_ids: List<Long>,
  val title: String?,
  val location: String?,
  val description: String?,
  val logo: String?,
  val country_id: Long,
  val embedded_landing_page: String?, // source files path
)

class OrganizationInfoDTO {
  var id: Long? = null
  var account: AccountDTO? = null
  var links: LinksInfoDTO? = null
  var activity_types: List<LabelValueDTO>? = null // to be set in service with data from external client
  var title: String? = null
  var location: String? = null
  var description: String? = null
  var logo: MediaDTO? = null
  var country: LabelValueDTO? = null // to be set in service with data from external client
  var embedded_landing_page: MediaDTO? = null
  // parsed landing for embedding

  fun toDTO(entity: Organization, account: AccountDTO): OrganizationInfoDTO {
    this.id = entity.id
    this.account = account
    this.links = entity.links?.let { LinksInfoDTO().toDTO(it) }
    this.title = entity.title
    this.location = entity.location
    this.description = entity.description
    this.logo = entity.logo?.let { MediaDTO(it) }
    this.embedded_landing_page = entity.embeddedLandingPage?.let { MediaDTO(it) }

    return this
  }
}
