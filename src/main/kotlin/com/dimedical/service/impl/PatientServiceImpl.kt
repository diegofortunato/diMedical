package com.dimedical.service.impl

import com.dimedical.dto.PatientDTO
import com.dimedical.entity.PatientEntity
import com.dimedical.repository.PatientRepository
import com.dimedical.service.PatientService
import com.dimedical.util.AppUtil
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Optional
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class PatientServiceImpl(
    private val patientRepository: PatientRepository
) : PatientService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun addPatient(patientRequest: PatientEntity): PatientDTO {
        log.info("Create Patient service. name={}", patientRequest.name)

        val patientDB = isPatientExist(patientRequest.document.cpf)
        if (patientDB.isPresent) throw EntityExistsException("Patient already exists.")

        patientRequest.document.cpf = AppUtil.normalizeField(patientRequest.document.cpf)
        patientRequest.document.rg = AppUtil.normalizeField(patientRequest.document.rg)

        return patientRepository.save(patientRequest).toDTO()
    }

    override fun findPatient(patientID: Long): PatientDTO {
        log.info("Find Patient service. id={}", patientID)

        val patientDB = patientRepository.findById(patientID)
            .orElseThrow { EntityNotFoundException("Patient not Exists") }

        return patientDB.toDTO()
    }

    override fun updatePatient(patientID: Long, patientRequest: PatientEntity): PatientDTO {
        log.info("Update Patient service. id={}", patientID)

        val patientDB = patientRepository.findById(patientID)
            .orElseThrow { EntityNotFoundException("Patient not Exists") }

        val patient = isPatientExist(patientRequest.document.cpf)

        if (patient.isPresent && patient.get().id == patientDB.id) {
            updateFieldsPatient(patientDB, patientRequest)
        } else {
            throw EntityExistsException("Document already exists in the system")
        }

        return patientRepository.save(patientDB).toDTO()
    }

    override fun deletePatient(patientID: Long) {
        log.info("Delete Patient service. id={}", patientID)

        val patientDB = patientRepository.findById(patientID)
            .orElseThrow { EntityNotFoundException("Patient not Exists") }

        patientRepository.delete(patientDB)
    }

    private fun isPatientExist(cpf: String): Optional<PatientEntity> {
        return patientRepository.findByDocumentCpf(cpf)
    }

    private fun updateFieldsPatient(patientDB: PatientEntity, patientRequest: PatientEntity) {
        patientDB.name = patientRequest.name
        patientDB.sex = patientRequest.sex
        patientDB.mothersName = patientRequest.mothersName
        patientDB.address = patientRequest.address
        patientDB.contact = patientRequest.contact
        patientDB.document = patientRequest.document
    }
}
