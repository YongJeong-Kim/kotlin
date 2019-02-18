package com.kyj.kotlindownloadupload

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(FileStorageProperties::class)
@SpringBootApplication
class KotlinDownloadUploadApplication

fun main(args: Array<String>) {
  runApplication<KotlinDownloadUploadApplication>(*args)
}

