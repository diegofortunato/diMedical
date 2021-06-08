package com.dimedical.entity

import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "medical_request_tb")
data class MedicalRequestEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_request_id")
    var id: Long,

    @OneToOne(fetch = FetchType.LAZY)
    var patient: PatientEntity?,

    @OneToOne(fetch = FetchType.LAZY)
    var doctor: DoctorEntity?,

    @OneToMany(mappedBy = "medicalRequest", fetch = FetchType.LAZY)
    val exams: List<ExamEntity>,

    @Column(name = "medical_request_created_at")
    val createdAt: Date
)
