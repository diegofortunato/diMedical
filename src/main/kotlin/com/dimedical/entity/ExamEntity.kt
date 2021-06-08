package com.dimedical.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "exam_tb")
data class ExamEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    var id: Long?,

    @Column(name = "exam_name")
    val name: String,

    @Column(name = "exam_expiration_date")
    val expirationDate: Date,

    @ManyToOne
    @JoinColumn(name = "medical_request_id")
    var medicalRequest: MedicalRequestEntity?,

    @Column(name = "exam_created_at")
    val createdAt: Date
)
