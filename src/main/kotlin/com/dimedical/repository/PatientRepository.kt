package com.dimedical.repository

import com.dimedical.entity.PatientEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface PatientRepository : JpaRepository<PatientEntity, Long> {

    fun findByDocumentCpf(cpf: String): Optional<PatientEntity>
}
