package o_o.service.profile.base.constant

object GlobalSettings {
  var PROJECT_PREFIX = "project"
  var API_PATH_PREFIX = "project/api/v1"
    get() = "$PROJECT_PREFIX/api/v1"

  var DEBUG = false
  var BASE_DOMAIN = ""
  var BASE_URL = ""
  var SCHEME = ""

  var BASE_UPLOADER_FOLDER = ""
  var BASE_UPLOADER_DOMAIN = ""
  var BASE_UPLOADER_URL = ""
  var UPLOADER_SCHEME = ""

  const val DEFAULT_PROFILE_PIC = "default_profile_pic.png"

  // LANGUAGES
  const val LANG_CODE_RU = "ru"
  const val LANG_CODE_EN = "en"
  const val LANG_CODE_UZ = "uz"
  const val DEFAULT_LANG = LANG_CODE_EN


  var MAX_IMAGE_THUMB_SIZE = 300
  var MAX_IMAGE_SIZE = 1000
  const val WATERMARKED_SUFFIX = "_wtm_kd"
  const val FILE_SIZE_PREFIX = "f_"
  const val FILE_SIZE_SUFFIX = "_s"
  const val FILES_STRING_SEPARATOR = "|"
  var WATERMARK_FILE_PATH = ""



  //SMS Broker
  var SMS_BROKER_URL = ""
  var SMS_BROKER_USERNAME = ""
  var SMS_BROKER_PASSWORD = ""
}
