package com.dimedical.service

import com.dimedical.dto.PatientDTO
import com.dimedical.entity.PatientEntity

interface PatientService {
    fun addPatient(patientRequest: PatientEntity): PatientDTO
    fun findPatient(patientID: Long): PatientDTO
    fun updatePatient(patientID: Long, patientRequest: PatientEntity): PatientDTO
    fun deletePatient(patientID: Long)
}
