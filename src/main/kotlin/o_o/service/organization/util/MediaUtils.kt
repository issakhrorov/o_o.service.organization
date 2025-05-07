package o_o.service.organization.util

import o_o.service.profile.base.constant.GlobalSettings.BASE_UPLOADER_FOLDER
import o_o.service.profile.base.constant.GlobalSettings.BASE_UPLOADER_URL
import o_o.service.profile.base.constant.GlobalSettings.MAX_IMAGE_SIZE
import o_o.service.profile.base.constant.GlobalSettings.MAX_IMAGE_THUMB_SIZE

fun getMediaUrl(filePath: String): String {
  return "$BASE_UPLOADER_URL/$filePath"
}

fun getBigThumbMediaUrl(filePath: String): String {
  if(filePath.startsWith("default"))
    return getMediaUrl(filePath)
  if(filePath.contains("_ntb"))
    return getMediaUrl(filePath)
  if(!isFilePathImage(filePath))
    return getMediaUrl(filePath)

  return when {
    filePath.contains("-$MAX_IMAGE_SIZE-$MAX_IMAGE_SIZE") -> "$BASE_UPLOADER_URL/$filePath"
    filePath.contains("-$MAX_IMAGE_THUMB_SIZE-$MAX_IMAGE_THUMB_SIZE") -> "$BASE_UPLOADER_URL/" + filePath.replace("-$MAX_IMAGE_THUMB_SIZE-$MAX_IMAGE_THUMB_SIZE", "-$MAX_IMAGE_SIZE-$MAX_IMAGE_SIZE")
    else -> {
      val thumbPath = convertToThumb(filePath, MAX_IMAGE_SIZE)
      "$BASE_UPLOADER_URL/$thumbPath"
    }
  }
}
fun getSmallThumbMediaUrl(filePath: String): String {
  if(filePath.startsWith("default"))
    return getMediaUrl(filePath)
  if(filePath.contains("_ntb"))
    return getMediaUrl(filePath)
  if(!isFilePathImage(filePath))
    return getMediaUrl(filePath)

  return when {
    filePath.contains("-$MAX_IMAGE_THUMB_SIZE-$MAX_IMAGE_THUMB_SIZE") -> "$BASE_UPLOADER_URL/$filePath"
    filePath.contains("-$MAX_IMAGE_SIZE-$MAX_IMAGE_SIZE") -> "$BASE_UPLOADER_URL/" + filePath.replace("-$MAX_IMAGE_SIZE-$MAX_IMAGE_SIZE", "-$MAX_IMAGE_THUMB_SIZE-$MAX_IMAGE_THUMB_SIZE")
    else -> {
      val thumbPath = convertToThumb(filePath, MAX_IMAGE_THUMB_SIZE)
      return "$BASE_UPLOADER_URL/$thumbPath"
    }
  }
}

fun isFilePathImage(filePath: String): Boolean {
  val fullFileName = filePath.substring(filePath.lastIndexOf("/") + 1)
  val extension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1, fullFileName.length)
  return when(extension) {
    "jpg", "png", "jpeg", "bmp" -> true
    else -> false
  }
}

private fun convertToThumb(filePath: String, thumbSize: Int): String {
  val rootPath = filePath.substring(0, filePath.lastIndexOf("/"))
  val fullFileName = filePath.substring(filePath.lastIndexOf("/") + 1)
  val name = fullFileName.substring(0, fullFileName.lastIndexOf("."))
  val extension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1, fullFileName.length)
  val nameWithThumb = "$name-$thumbSize-$thumbSize"
  return "$rootPath/$nameWithThumb.$extension"
}

fun getMediaFullPath(src: String): String {
  if(BASE_UPLOADER_FOLDER.isNullOrBlank())
    return src
  return "$BASE_UPLOADER_FOLDER/$src"
}


fun parseMediaSize(src: String): Long {
  if(src.isBlank())
    return 0
  val regex = "f_\\d*_s".toRegex()
  val matchResult = regex.find(src) ?: return 0
  val resultStr = matchResult.value
  return resultStr.filter { it.isDigit() }.toLongOrNull() ?: 0
}


fun getExtensionFromName(filePath: String): String {
  val fullFileName = filePath.substring(filePath.lastIndexOf("/") + 1)
  return fullFileName.substring(fullFileName.lastIndexOf(".") + 1, fullFileName.length)
}
