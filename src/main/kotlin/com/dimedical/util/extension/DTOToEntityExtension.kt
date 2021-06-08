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
import java.util.*

object DTOToEntityExtension {

    fun PatientDTO.toEntity() = PatientEntity(
        this.id,
        this.name,
        this.birthDate,
        this.sex,
        this.mothersName,
        this.address.toEntity(),
        this.contact.toEntity(),
        this.document.toEntity(),
        Date()
    )

    fun AddressDTO.toEntity() = AddressEntity(
        this.id,
        this.street,
        this.number,
        this.cep,
        this.district,
        this.city,
        this.state,
        null,
        Date()
    )

    fun ContactDTO.toEntity() = ContactEntity(
        this.id,
        this.telephone,
        this.email,
        null,
        Date()
    )

    fun DocumentDTO.toEntity() = DocumentEntity(
        this.cpf,
        this.rg,
        null,
        Date()
    )

    fun DoctorDTO.toEntity() = DoctorEntity(
        this.id,
        this.name,
        this.adviceNumber,
        this.adviceState,
        this.adviceType,
        Date()
    )

    fun MedicalRequestDTO.toEntity() = MedicalRequestEntity(
        this.id,
        null,
        null,
        this.exams.toEntityList(),
        Date()
    )

    private fun List<ExamDTO>.toEntityList() = this.map { item -> item.toEntity() }

    fun ExamDTO.toEntity() = ExamEntity(
        this.id,
        this.name,
        this.expirationDate,
        null,
        Date()
    )
}
