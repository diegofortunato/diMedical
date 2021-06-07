package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class MedicalRequestDTO(

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("paciente")
    @NotEmpty(message = "Paciente não pode ser nulo")
    val patient: PatientDTO,

    @JsonProperty("doutor(a)")
    @NotEmpty(message = "Doutor(a) não pode ser nulo")
    val doctor: DoctorDTO,

    @JsonProperty("exames")
    @NotEmpty(message = "Exames não pode ser nulo")
    val exams: List<ExamDTO>,
)
