package o_o.service.organization.model.vacancy

import o_o.service.organization.base.model.BaseModel
import o_o.service.organization.model.organization.Organization
import o_o.service.organization.model.vacancy.mappings.*
import org.babyfish.jimmer.sql.*
import java.util.*

@Entity
interface Vacancy: BaseModel {
  @ManyToOne
  val organization: Organization

  val title: String
  val description: String
  val salaryFrom: Long?
  val salaryTo: Long?
  val status: Int // VacancyStatus
  val experienceRange: Int? // ExperienceRange
  val requiredEducationType: Int? // EducationType
  val jobShift: Int? // JobShift
  val workingHours: Int?
  val validUntil: Date?

  //  val acceptableCertificates  - for future integration with education platform from which
  //                                some courses can be acceptable to increase candidates chances
  @OneToMany(mappedBy = "vacancy")
  val skills: List<VacancySkillMap>
  @OneToMany(mappedBy = "vacancy")
  val responsibilities: List<VacancyResponsibilityMap>
  @OneToMany(mappedBy = "vacancy")
  val matchingResumes: List<VacancyMatchingResumesMap>
  @OneToMany(mappedBy = "vacancy")
  val requirements: List<VacancyRequirementsMap>
  @OneToMany(mappedBy = "vacancy")
  val privileges: List<VacancyPrivilegesMap>
  @OneToMany(mappedBy = "vacancy")
  val jobTypes: List<VacancyJobTypesMap>
  @OneToMany(mappedBy = "vacancy")
  val requiredLanguages: List<VacancyRequiredLanguagesMap>
}