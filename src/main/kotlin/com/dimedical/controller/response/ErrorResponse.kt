package com.dimedical.controller.response

data class ErrorResponse(
    val date: String,
    val code: Int,
    val error: String,
    val description: String
)
