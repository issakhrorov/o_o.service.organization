package o_o.service.organization.base.media

import o_o.service.organization.util.*

class MediaDTO {
  var original_name: String
  var src: String

  val url: String
    get() = getBigThumbMediaUrl(this.src)

  val thumb_url: String
    get() = getSmallThumbMediaUrl(this.src)

  val original_url: String
    get() = getMediaUrl(this.src)

  val extension: String
    get() = getExtensionFromName(this.src)

  val file_size: Long
    get() = parseMediaSize(this.src)

  constructor(original_name: String, src: String) {
    this.original_name = original_name
    this.src = src
  }

  constructor(src: String) {
    this.src = src
    val name = src.substring(src.lastIndexOf("/") + 1)
    val indexLast = name.lastIndexOf(".")
    this.original_name = if(indexLast > 0) name.substring(0, indexLast) else if(name.isBlank()) "no-name" else name
  }

}
