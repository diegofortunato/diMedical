package com.dimedical.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PatientDTO(

    @JsonProperty("id")
    val userId: Long,
)
