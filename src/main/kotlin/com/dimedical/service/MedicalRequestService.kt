package com.dimedical.service

import com.dimedical.dto.MedicalRequestDTO
import com.dimedical.entity.MedicalRequestEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface MedicalRequestService {
    fun addMedicalRequest(medicalRequestEntity: MedicalRequestEntity, patientID: Long, doctorID: Long): MedicalRequestDTO
    fun findMedicalRequest(medicalRequestID: Long): MedicalRequestDTO
    fun findAllMedicalRequest(paging: Pageable): Page<MedicalRequestDTO>
}
