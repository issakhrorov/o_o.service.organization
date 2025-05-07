package o_o.service.organization.model

enum class ProfileStatus constructor(val code: Int) {
  IN_PROGRESS(0),
  ACTIVE(1),
  INACTIVE(2),
  HIDDEN(3),
  ;


  companion object {
    fun fromCode(code: Int): ProfileStatus {
      for (status in entries) {
        if (status.code == code) {
          return status
        }
      }
      throw UnsupportedOperationException(
        "The ProfileStatus code $code is not supported!"
      )
    }
  }
}

enum class VacancyStatus constructor(val code: Int) {
  ACTIVE(0),
  DRAFT(1),
  INACTIVE(2),
  CLOSED(3),
  ;

  companion object {
    fun fromCode(code: Int): VacancyStatus {
      for (item in entries) {
        if (item.code == code) {
          return item
        }
      }
      throw UnsupportedOperationException(
        "The VacancyStatus code $code is not supported!"
      )
    }
  }
}

enum class ExperienceRange constructor(val code: Int) {
  NO_EXPERIENCE_REQUIRED(0),
  ONE_TO_THREE_YEARS(1),
  THREE_TO_SIX_YEARS(2),
  MORE_THAN_SIX_YEARS(3),
  ;

  companion object {
    fun fromCode(code: Int): ExperienceRange {
      for (item in entries) {
        if (item.code == code) {
          return item
        }
      }
      throw UnsupportedOperationException(
        "The VacancyStatus code $code is not supported!"
      )
    }
  }
}


enum class JobShift constructor(val code: Int) {
  MORNING_SHIFT(0),
  EVENING_SHIFT(1),
  NIGHT_SHIFT(2),
  FLEXIBLE(3),
  OTHER(100)
  ;

  companion object {
    fun fromCode(code: Int): JobShift {
      for (item in entries) {
        if (item.code == code) {
          return item
        }
      }
      throw UnsupportedOperationException(
        "The JobShift code $code is not supported!"
      )
    }
  }
}

enum class VacancyJobType constructor(val code: Int) {
  REMOTE(0),
  ON_SITE(1),
  HYBRID(2),
  CONTRACT(3),
  FULL_TIME(4),
  PART_TIME(5),
  INTERNSHIP(6),
  FREELANCE(7),
  RELOCATED(8),
  TEMPORARY(9),
  VOLUNTEER(10),
  ;

  companion object {
    fun fromCode(code: Int): VacancyJobType {
      for (item in entries) {
        if (item.code == code) {
          return item
        }
      }
      throw UnsupportedOperationException(
        "The VacancyJobType code $code is not supported!"
      )
    }
  }
}

enum class EducationType constructor(val code: Int) {
  HIGH_SCHOOL_DEGREE(0),
  BACHELORS_DEGREE(1),


  ;

  companion object {
    fun fromCode(code: Int): EducationType {
      for (item in entries) {
        if (item.code == code)
          return item
      }
      throw UnsupportedOperationException(
        "The EducationType code $code is not supported"
      )
    }
  }
}