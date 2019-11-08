package com.kyj.kotlindownloadupload

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import javax.servlet.http.HttpServletRequest

@RestController
class HomeController(val fileStorageService: FileStorageService) {
  val logger = LoggerFactory.getLogger(HomeController::class.java)

  @PostMapping("uploadFile")
  fun uploadFile(file: MultipartFile): UploadFileResponse {
    val filename = fileStorageService.storeFile(file)
    val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("/downloadFile/")
      .path(filename)
      .toUriString()
    return UploadFileResponse(filename, fileDownloadUri, file.contentType, file.size)
  }

  @PostMapping("/uploadMultipleFiles")
  fun uploadMultipleFiles(files: Array<MultipartFile>) = files
    .map { uploadFile(it) }
    .toCollection(mutableListOf())

  @GetMapping("downloadFile/{filename:.+}")
  fun downloadFile(@PathVariable filename: String, request: HttpServletRequest): ResponseEntity<Resource> {
    val resource = fileStorageService.loadFileAsResource(filename)
    var contentType = ""
    try {
      contentType = request.servletContext.getMimeType(resource.file.absolutePath)
    } catch (e: IOException) {
      logger.info("Could not determine file type.")
    }
    if (contentType.isNullOrEmpty())
      contentType = "application/octet-stream"
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${resource.filename}\"")
      .body(resource)
  }
}