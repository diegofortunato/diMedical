package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.service.MedicalRequestService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class MedicalRequestController(private val medicalRequestService: MedicalRequestService) {

    private val log = LoggerFactory.getLogger(javaClass)
}
