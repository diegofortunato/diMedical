package com.dimedical.constant

class APIConstant {

    companion object {
        const val BASE_API = ""

        const val POST_PATIENT = "/patient"
        const val GET_PATIENT = "/patient/{id}"
        const val DELETE_PATIENT = "/patient/{id}"
        const val PATCH_PATIENT = "/patient/{id}"

        const val POST_DOCTOR = "/doctor"
        const val GET_DOCTOR = "/doctor/{id}"
        const val DELETE_DOCTOR = "/doctor/{id}"
        const val PATCH_DOCTOR = "/doctor/{id}"

        const val POST_MEDICAL_REQUEST = "/exam"
        const val GET_MEDICAL_REQUEST = "/exam/{id}"
        const val GET_ALL_MEDICAL_REQUEST = "/exam"

        const val ERROR_400 = "Usuário já existe no sistema."
        const val DETAILS_ERROR_400 = "O usuário já existe no sistema, " +
            "verifique o número do CPF ou o Numero do conselho."

        const val ERROR_404 = "Usuário não existe no sistema."
        const val DETAILS_ERROR_404 = "O usuário não existe no sistema, " +
            "verifique o seu ID."

        const val ERROR_412 = "Existem campos com inconsistências."

        const val ERROR_500 = "Erro interno do servidor."
        const val DETAILS_ERROR_500 = "Ocoreu um erro interno no servidor, " +
            "entre em contato com o administrador do sistema."
    }
}
