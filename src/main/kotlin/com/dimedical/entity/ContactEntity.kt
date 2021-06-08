package com.dimedical.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "contact_tb")
data class ContactEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    var id: Long,

    @Column(name = "contact_telephone")
    val telephone: String,

    @Column(name = "contact_email")
    val email: String,

    @OneToOne(mappedBy = "contact")
    val patient: PatientEntity?,

    @Column(name = "contact_created_at")
    val createdAt: Date
)