package o_o.service.organization.service.organization

import o_o.service.organization.model.organization.Organization
import o_o.service.organization.service.organization.ProfileRequiredFields.ORGANIZATION_REQUIRED_FIELDS
import kotlin.reflect.full.memberProperties

object ProfileRequiredFields {
  val ORGANIZATION_REQUIRED_FIELDS = listOf(
    Organization::links.name,
//    Organization::activityTypes.name,
    Organization::title.name,
    Organization::location.name,
    Organization::description.name,
    Organization::logo.name,
    Organization::countryId.name,
  )
}

fun validateFields(organization: Organization) {
  for (fieldName in ORGANIZATION_REQUIRED_FIELDS) {
    val property = organization::class.memberProperties.find { it.name == fieldName }
      ?: throw IllegalArgumentException("Field '$fieldName' does not exist in Organization class")

    property.call(organization)
      ?: throw IllegalStateException("Validation failed: Field '$fieldName' must not be null.")
  }
}