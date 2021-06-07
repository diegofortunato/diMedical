package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import javax.validation.constraints.NotEmpty

data class ExamDTO(

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("nome")
    @NotEmpty(message = "Nome do exame não pode ser nulo")
    val name: String,

    @JsonProperty("data_expiracao")
    @NotEmpty(message = "Data de expiracao do exame não pode ser nulo")
    val expirationDate: Date,
)