package com.dimedical.service

import com.dimedical.dto.DoctorDTO
import com.dimedical.entity.DoctorEntity

interface DoctorService {
    fun addDoctor(doctorRequest: DoctorEntity): DoctorDTO
    fun findDoctor(doctorID: Long): DoctorDTO
    fun updateDoctor(doctorID: Long, doctorRequest: DoctorEntity): DoctorDTO
    fun deleteDoctor(doctorID: Long)
}
