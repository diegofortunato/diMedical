package com.dimedical.entity

import com.dimedical.constant.StateEnum
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "doctor_tb")
data class DoctorEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    var id: Long,

    @Column(name = "doctor_name")
    val name: String,

    @Column(name = "doctor_advice_number")
    val adviceNumber: Number,

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_advice_state")
    val adviceState: StateEnum,

    @Column(name = "doctor_advice_type")
    val adviceType: String,

    @Column(name = "doctor_created_at")
    val createdAt: Date,

    @Column(name = "doctor_updated_at")
    val updatedAt: Date
)
