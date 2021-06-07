package com.dimedical.controller.advice

import com.dimedical.constant.APIConstant
import com.dimedical.controller.response.ErrorResponse
import com.dimedical.controller.response.Response
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.Instant

@ControllerAdvice
class RestExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(ex: Exception, request: WebRequest):
        ResponseEntity<Response<ErrorResponse>> {
        log.error("Error in handleException: {}", ex.message)

        val errorResponse = ErrorResponse(
            Instant.now().toString(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            APIConstant.ERROR_500,
            APIConstant.DETAILS_ERROR_500
        )
        val response = Response(data = errorResponse)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
