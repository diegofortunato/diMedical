package com.dimedical.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "medical_request_tb")
data class MedicalRequestEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_request_id")
    var id: Long,

    @OneToMany
    @JoinColumn(name = "exam_id")
    val exams: List<ExamEntity>,

    @Column(name = "medical_request_created_at")
    val createdAt: Date,

    @Column(name = "medical_request_updated_at")
    val updatedAt: Date
)
