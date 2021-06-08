package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.controller.request.Request
import com.dimedical.controller.response.Response
import com.dimedical.dto.DoctorDTO
import com.dimedical.service.DoctorService
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
class DoctorController(private val doctorService: DoctorService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.POST_DOCTOR)
    fun newDoctor(@Valid @RequestBody doctorRequest: Request<DoctorDTO>):
        ResponseEntity<Response<DoctorDTO>> {
        log.info("POST ${APIConstant.POST_DOCTOR} for new Doctor name: {}", doctorRequest.request.name)

        val response = doctorService.addDoctor(doctorRequest.request.toEntity())

        return ResponseEntity
            .created(URI.create(URLEncoder.encode(APIConstant.BASE_API + APIConstant.GET_DOCTOR, "UTF-8")))
            .body(Response(data = response))
    }

    @GetMapping(APIConstant.GET_DOCTOR)
    fun findDoctor(@PathVariable("id") doctorID: Long): ResponseEntity<Response<DoctorDTO>> {
        log.info("GET ${APIConstant.GET_DOCTOR} for ID {}", doctorID)

        val response = doctorService.findDoctor(doctorID)
        return ResponseEntity.ok(Response(data = response))
    }

    @PatchMapping(APIConstant.PATCH_DOCTOR)
    fun updateDoctor(@PathVariable("id") doctorID: Long, @Valid @RequestBody doctorRequest: Request<DoctorDTO>):
        ResponseEntity<Response<DoctorDTO>> {
        log.info("PATCH ${APIConstant.PATCH_DOCTOR} for ID {}", doctorRequest.request.id)

        val response = doctorService.updateDoctor(doctorID, doctorRequest.request.toEntity())
        return ResponseEntity.ok(Response(data = response))
    }

    @DeleteMapping(APIConstant.DELETE_DOCTOR)
    fun deleteDoctor(@PathVariable("id") doctorID: Long): ResponseEntity<Response<DoctorDTO>> {
        log.info("DELETE ${APIConstant.DELETE_DOCTOR} for ID {}", doctorID)

        doctorService.deleteDoctor(doctorID)
        return ResponseEntity.noContent().build()
    }
}
