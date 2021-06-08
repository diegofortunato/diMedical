package com.dimedical.repository

import com.dimedical.entity.DoctorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DoctorRepository : JpaRepository<DoctorEntity, Long> {

    fun findByAdviceNumber(adviceNumber: Number): Optional<DoctorEntity>
}
