package com.kyj.kotlindownloadupload

import java.lang.RuntimeException

class FileStorageException: RuntimeException {
  constructor(message: String, cause: Throwable)
}