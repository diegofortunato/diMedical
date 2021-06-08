package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MedicalRequestDTO(

    @JsonIgnore
    var id: Long,

    @JsonProperty("paciente_id")
    @NotEmpty(message = "ID do Paciente não pode ser nulo")
    val patientId: Long?,

    @JsonProperty("paciente")
    val patient: PatientDTO?,

    @JsonProperty("medico(a)_id")
    @NotEmpty(message = "ID do medico(a) não pode ser nulo")
    val doctorId: Long?,

    @JsonProperty("medico(a)")
    val doctor: DoctorDTO?,

    @JsonProperty("exames")
    @NotEmpty(message = "Exames não pode ser nulo")
    val exams: List<ExamDTO>,
)
