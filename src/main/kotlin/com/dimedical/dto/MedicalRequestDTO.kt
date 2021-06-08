package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class MedicalRequestDTO(

    @JsonIgnore
    var id: Long,

    @JsonProperty("paciente")
    @NotEmpty(message = "ID do Paciente não pode ser nulo")
    val patientId: Long,

    @JsonProperty("medico(a)")
    @NotEmpty(message = "ID do medico(a) não pode ser nulo")
    val doctorId: Long,

    @JsonProperty("exames")
    @NotEmpty(message = "Exames não pode ser nulo")
    val exams: List<ExamDTO>,
)
