package com.dimedical.repository

import com.dimedical.entity.MedicalRequestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicalRequestRepository : JpaRepository<MedicalRequestEntity, Long>
