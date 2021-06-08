package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.controller.request.Request
import com.dimedical.controller.response.Response
import com.dimedical.controller.response.ResponsePagination
import com.dimedical.dto.MedicalRequestDTO
import com.dimedical.service.MedicalRequestService
import com.dimedical.util.extension.DTOToEntityExtension.toEntity
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class MedicalRequestController(private val medicalRequestService: MedicalRequestService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.POST_MEDICAL_REQUEST)
    fun newMedicalRequest(@Valid @RequestBody medicalRequest: Request<MedicalRequestDTO>):
        ResponseEntity<Response<MedicalRequestDTO>> {
        log.info("POST ${APIConstant.POST_MEDICAL_REQUEST} for new Medical Request")

        val response = medicalRequestService
            .addMedicalRequest(medicalRequest.request.toEntity(), medicalRequest.request.patientId!!, medicalRequest.request.doctorId!!)

        return ResponseEntity
            .created(URI.create(URLEncoder.encode(APIConstant.BASE_API + APIConstant.GET_MEDICAL_REQUEST, "UTF-8")))
            .body(Response(data = response))
    }

    @GetMapping(APIConstant.GET_MEDICAL_REQUEST)
    fun findMedicalRequest(@PathVariable("id") medicalRequestID: Long): ResponseEntity<Response<MedicalRequestDTO>> {
        log.info("GET ${APIConstant.GET_MEDICAL_REQUEST} for ID {}", medicalRequestID)

        val response = medicalRequestService.findMedicalRequest(medicalRequestID)
        return ResponseEntity.ok(Response(data = response))
    }

    @GetMapping(APIConstant.GET_ALL_MEDICAL_REQUEST)
    fun findAllMedicalRequest(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int,
        @RequestParam(name = "orderBy", defaultValue = "id") sortBy: String
    ): ResponseEntity<ResponsePagination<List<MedicalRequestDTO>>> {
        log.info("GET ${APIConstant.GET_ALL_MEDICAL_REQUEST}")

        val paging = PageRequest.of(page, size, Sort.by(sortBy))
        val response = medicalRequestService.findAllMedicalRequest(paging)
        return ResponseEntity.ok(
            ResponsePagination(
                data = response.content,
                totalPages = response.totalPages,
                currentPage = response.number,
                totalItems = response.totalElements
            )
        )
    }
}
