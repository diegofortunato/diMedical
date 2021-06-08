package com.dimedical.dto

import com.dimedical.constant.StateEnum
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class DoctorDTO(

    @JsonIgnore
    var id: Long,

    @JsonProperty("nome")
    @NotEmpty(message = "Nome do medico não pode ser nulo")
    val name: String,

    @JsonProperty("numero_conselho")
    @NotEmpty(message = "Numero do conselho não pode ser nulo")
    val adviceNumber: Number,

    @JsonProperty("estado_conselho")
    @NotEmpty(message = "Estado do conselho não pode ser nulo")
    val adviceState: StateEnum,

    @JsonProperty("tipo_conselho")
    @NotEmpty(message = "Tipo do conselho não pode ser nulo")
    val adviceType: String
)
