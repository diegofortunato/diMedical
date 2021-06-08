package com.dimedical.service.impl

import com.dimedical.dto.DoctorDTO
import com.dimedical.entity.DoctorEntity
import com.dimedical.repository.DoctorRepository
import com.dimedical.service.DoctorService
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Optional
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class DoctorServiceImpl(
    private val doctorRepository: DoctorRepository
) : DoctorService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun addDoctor(doctorRequest: DoctorEntity): DoctorDTO {
        log.info("Create Doctor service. name={}", doctorRequest.name)

        val doctorDB = isDoctorExist(doctorRequest.adviceNumber)
        if (doctorDB.isPresent) throw EntityExistsException("Doctor already exists.")

        return doctorRepository.save(doctorRequest).toDTO()
    }

    override fun findDoctor(doctorID: Long): DoctorDTO {
        log.info("Find Doctor service. id={}", doctorID)

        val doctorDB = doctorRepository.findById(doctorID)
            .orElseThrow { EntityNotFoundException("Doctor not Exists") }

        return doctorDB.toDTO()
    }

    override fun updateDoctor(doctorID: Long, doctorRequest: DoctorEntity): DoctorDTO {
        log.info("Update Doctor service. id={}", doctorID)

        val doctorDB = doctorRepository.findById(doctorID)
            .orElseThrow { EntityNotFoundException("Doctor not Exists") }

        val doctor = isDoctorExist(doctorRequest.adviceNumber)

        if (doctor.isPresent && doctor.get().id == doctorDB.id) {
            updateFieldsDoctor(doctorDB, doctorRequest)
        } else {
            throw EntityExistsException("Advice type already exists in the system")
        }

        return doctorRepository.save(doctorDB).toDTO()
    }

    override fun deleteDoctor(doctorID: Long) {
        log.info("Delete Doctor service. id={}", doctorID)

        val doctorDB = doctorRepository.findById(doctorID)
            .orElseThrow { EntityNotFoundException("Doctor not Exists") }

        doctorRepository.delete(doctorDB)
    }

    private fun isDoctorExist(adviceNumber: Number): Optional<DoctorEntity> {
        return doctorRepository.findByAdviceNumber(adviceNumber)
    }

    private fun updateFieldsDoctor(doctorDB: DoctorEntity, doctorRequest: DoctorEntity) {
        doctorDB.name = doctorRequest.name
        doctorDB.adviceNumber = doctorRequest.adviceNumber
        doctorDB.adviceState = doctorRequest.adviceState
        doctorDB.adviceType = doctorRequest.adviceType
    }
}
