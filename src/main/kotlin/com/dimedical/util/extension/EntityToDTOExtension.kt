package com.dimedical.util.extension

import com.dimedical.dto.AddressDTO
import com.dimedical.dto.ContactDTO
import com.dimedical.dto.DoctorDTO
import com.dimedical.dto.DocumentDTO
import com.dimedical.dto.ExamDTO
import com.dimedical.dto.MedicalRequestDTO
import com.dimedical.dto.PatientDTO
import com.dimedical.entity.AddressEntity
import com.dimedical.entity.ContactEntity
import com.dimedical.entity.DoctorEntity
import com.dimedical.entity.DocumentEntity
import com.dimedical.entity.ExamEntity
import com.dimedical.entity.MedicalRequestEntity
import com.dimedical.entity.PatientEntity

object EntityToDTOExtension {

    fun PatientEntity.toDTO() = PatientDTO(
        this.id,
        this.name,
        this.birthDate,
        this.sex,
        this.mothersName,
        this.address.toDTO(),
        this.contact.toDTO(),
        this.document.toDTO()
    )

    fun DoctorEntity.toDTO() = DoctorDTO(
        this.id,
        this.name,
        this.adviceNumber,
        this.adviceState,
        this.adviceType
    )

    private fun AddressEntity.toDTO() = AddressDTO(
        this.id,
        this.street,
        this.number,
        this.cep,
        this.district,
        this.city,
        this.state
    )

    private fun ContactEntity.toDTO() = ContactDTO(
        this.id,
        this.telephone,
        this.email
    )

    private fun DocumentEntity.toDTO() = DocumentDTO(
        this.cpf,
        this.rg
    )

    fun MedicalRequestEntity.toDTO() = MedicalRequestDTO(
        this.id,
        null,
        this.patient!!.toDTO(),
        null,
        this.doctor!!.toDTO(),
        this.exams.map { examEntity -> examEntity.toDTO() }
    )

    fun ExamEntity.toDTO() = ExamDTO(
        this.id!!,
        this.name,
        this.expirationDate
    )
}
