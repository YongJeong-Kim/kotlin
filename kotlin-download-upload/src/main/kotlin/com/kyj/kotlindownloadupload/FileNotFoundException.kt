package com.kyj.kotlindownloadupload

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class FileNotFoundException: RuntimeException {
  constructor(message: String)
  constructor(message: String, cause: Throwable)
}