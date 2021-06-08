package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class DocumentDTO(

    @JsonProperty("cpf")
    @NotEmpty(message = "CPF não pode ser nulo")
    val cpf: String,

    @JsonProperty("rg")
    @NotEmpty(message = "RG não pode ser nulo")
    val rg: String
)
