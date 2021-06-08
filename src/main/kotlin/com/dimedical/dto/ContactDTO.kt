package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class ContactDTO(

    @JsonIgnore
    var id: Long,

    @JsonProperty("telefone")
    @NotEmpty(message = "Telefone não pode ser nulo")
    val telephone: String,

    @JsonProperty("email")
    @NotEmpty(message = "Email não pode ser nulo")
    val email: String
)
