package com.dimedical.dto

import com.dimedical.constant.SexEnum
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class PatientDTO(

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("nome")
    @NotEmpty(message = "Nome do paciente não pode ser nulo")
    val name: String,

    @JsonProperty("data_nascimento")
    @NotEmpty(message = "Data de nascimento não pode ser nulo")
    val birthDate: String,

    @JsonProperty("sexo")
    @NotEmpty(message = "Sexo não pode ser nulo")
    val sex: SexEnum,

    @JsonProperty("nome_mae")
    @NotEmpty(message = "Nome da mae não pode ser nulo")
    val mothersName: String,

    @JsonProperty("endereco")
    @NotEmpty(message = "Endereco não pode ser nulo")
    val address: AddressDTO,

    @JsonProperty("contato")
    @NotEmpty(message = "Contato não pode ser nulo")
    val contact: ContactDTO,

    @JsonProperty("document")
    @NotEmpty(message = "Documento não pode ser nulo")
    val document: DocumentDTO
)
