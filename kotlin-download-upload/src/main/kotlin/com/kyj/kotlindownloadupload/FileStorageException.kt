package com.kyj.kotlindownloadupload

import java.lang.RuntimeException

class FileStorageException: RuntimeException {
  constructor(message: String, cause: Throwable): super(message, cause)
  constructor(message: String): super(message)
  constructor(cause: Throwable): super(cause)
}