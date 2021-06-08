package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.controller.request.Request
import com.dimedical.controller.response.Response
import com.dimedical.dto.PatientDTO
import com.dimedical.service.PatientService
import com.dimedical.util.extension.DTOToEntityExtension.toEntity
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class PatientController(private val patientService: PatientService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.POST_PATIENT)
    fun newPatient(@Valid @RequestBody patientRequest: Request<PatientDTO>):
        ResponseEntity<Response<PatientDTO>> {
        log.info("POST ${APIConstant.POST_PATIENT} for new Patient name: {}", patientRequest.request.name)

        val response = patientService.addPatient(patientRequest.request.toEntity())

        return ResponseEntity
            .created(URI.create(URLEncoder.encode(APIConstant.BASE_API + APIConstant.GET_DOCTOR, "UTF-8")))
            .body(Response(data = response))
    }

    @GetMapping(APIConstant.GET_PATIENT)
    fun findPatient(@PathVariable("id") patientID: Long): ResponseEntity<Response<PatientDTO>> {
        log.info("GET ${APIConstant.GET_PATIENT} for ID {}", patientID)

        val response = patientService.findPatient(patientID)
        return ResponseEntity.ok(Response(data = response))
    }

    @PatchMapping(APIConstant.PATCH_PATIENT)
    fun updatePatient(@PathVariable("id") patientID: Long, @Valid @RequestBody patientRequest: Request<PatientDTO>):
        ResponseEntity<Response<PatientDTO>> {
        log.info("PATCH ${APIConstant.PATCH_PATIENT} for ID {}", patientRequest.request.id)

        val response = patientService.updatePatient(patientID, patientRequest.request.toEntity())
        return ResponseEntity.ok(Response(data = response))
    }

    @DeleteMapping(APIConstant.DELETE_PATIENT)
    fun deletePatient(@PathVariable("id") patientID: Long): ResponseEntity<Response<PatientDTO>> {
        log.info("DELETE ${APIConstant.DELETE_PATIENT} for ID {}", patientID)

        patientService.deletePatient(patientID)
        return ResponseEntity.noContent().build()
    }
}
