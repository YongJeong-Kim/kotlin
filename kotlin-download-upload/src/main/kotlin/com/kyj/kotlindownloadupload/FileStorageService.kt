package com.kyj.kotlindownloadupload

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageService(val fileStorageProperties: FileStorageProperties) {
  val fileStorageLocation: Path
  init {
    this.fileStorageLocation = Paths.get(fileStorageProperties.uploadDir)
    try {
      Files.createDirectories(this.fileStorageLocation)
    } catch (e: Exception) {
      throw FileStorageException("Could not create the directory where the uploaded files will be stored.", e)
    }
  }

  fun storeFile(file: MultipartFile): String {
    val filename = StringUtils.cleanPath(file.originalFilename.toString())
    try {
      if (filename.contains("..")) {
        throw FileStorageException("Sorry! Filename contains invalid path sequence $filename")
      }
      val targetLocation = this.fileStorageLocation.resolve(filename)
      Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
      return filename
    } catch (e: IOException) {
      throw FileStorageException("Could not store file $filename. Please try again!", e)
    }
  }

  fun loadFileAsResourc(filename: String): Resource {
    try {
      val filePath = this.fileStorageLocation.resolve(filename).normalize()
      val resource = UrlResource(filePath.toUri())
      if (resource.exists())
        return resource
      else
        throw FileNotFoundException("File not found $filename")
    } catch (e: MalformedURLException) {
      throw FileNotFoundException("File not found $filename", e)
    }

  }
}