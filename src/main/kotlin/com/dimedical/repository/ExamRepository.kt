package com.dimedical.repository

import com.dimedical.entity.ExamEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository : JpaRepository<ExamEntity, Long>