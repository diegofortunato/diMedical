package com.dimedical.service.impl

import com.dimedical.repository.MedicalRequestRepository
import com.dimedical.service.MedicalRequestService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MedicalRequestServiceImpl(private val medicalRequestRepository: MedicalRequestRepository) :
    MedicalRequestService {

    private val log = LoggerFactory.getLogger(javaClass)
}
