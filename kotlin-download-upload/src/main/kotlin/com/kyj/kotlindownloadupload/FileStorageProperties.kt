package com.kyj.kotlindownloadupload

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file")
class FileStorageProperties(
//  @Value("\${file.uploadDir}")
  var uploadDir: String? = null
)