package com.kyj.kotlindownloadupload

class UploadFileResponse(
  var filename: String,
  var fileDownloadUri: String,
  var fileType: String?,
  var size: Long
)