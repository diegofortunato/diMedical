package com.dimedical.service.impl

import com.dimedical.dto.MedicalRequestDTO
import com.dimedical.entity.MedicalRequestEntity
import com.dimedical.repository.ExamRepository
import com.dimedical.repository.MedicalRequestRepository
import com.dimedical.service.DoctorService
import com.dimedical.service.MedicalRequestService
import com.dimedical.service.PatientService
import com.dimedical.util.extension.DTOToEntityExtension.toEntity
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MedicalRequestServiceImpl(
    private val medicalRequestRepository: MedicalRequestRepository,
    private val examRepository: ExamRepository,
    private val patientService: PatientService,
    private val doctorService: DoctorService
) : MedicalRequestService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun addMedicalRequest(medicalRequestEntity: MedicalRequestEntity, patientID: Long, doctorID: Long): MedicalRequestDTO {
        log.info("Init add medical request service")

        medicalRequestEntity.patient = patientService.findPatient(patientID).toEntity()
        medicalRequestEntity.doctor = doctorService.findDoctor(doctorID).toEntity()

        val medicalRequestDB = medicalRequestRepository.save(medicalRequestEntity)

        medicalRequestEntity.exams.forEach { examEntity ->
            examEntity.medicalRequest = medicalRequestDB
            examRepository.save(examEntity)
        }

        return medicalRequestDB.toDTO()
    }

    override fun findMedicalRequest(medicalRequestID: Long): MedicalRequestDTO {
        log.info("Find Medical Request service. id={}", medicalRequestID)

        val medicalRequestDB = medicalRequestRepository.findById(medicalRequestID)
            .orElseThrow { EntityNotFoundException("Medical Request not Exists") }

        return medicalRequestDB.toDTO()
    }

    override fun findAllMedicalRequest(paging: Pageable): Page<MedicalRequestDTO> {
        log.info("Find all Medical Request service")

        val medicalRequestsDB = medicalRequestRepository.findAll(paging)

        return medicalRequestsDB.map { medicalRequestEntity -> medicalRequestEntity.toDTO() }
    }
}
