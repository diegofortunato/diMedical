package com.dimedical.dto

import com.dimedical.constant.StateEnum
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class AddressDTO(
    @JsonProperty("id")
    var id: Long,

    @JsonProperty("rua")
    @NotEmpty(message = "Nome da rua não pode ser nulo")
    val street: String,

    @JsonProperty("numero")
    @NotEmpty(message = "Numero da rua não pode ser nulo")
    val number: Number,

    @JsonProperty("cep")
    @NotEmpty(message = "CEP não pode ser nulo")
    val cep: String,

    @JsonProperty("bairro")
    @NotEmpty(message = "Bairro não pode ser nulo")
    val district: String,

    @JsonProperty("cidade")
    @NotEmpty(message = "Cidade não pode ser nulo")
    val city: String,

    @JsonProperty("estado")
    @NotEmpty(message = "Estado não pode ser nulo")
    val state: StateEnum
)
