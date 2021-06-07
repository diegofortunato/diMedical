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
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "address_tb")
data class AddressEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    var id: Long,

    @Column(name = "address_street")
    val street: String,

    @Column(name = "address_number")
    val number: Number,

    @Column(name = "address_cep")
    val cep: String,

    @Column(name = "address_district")
    val district: String,

    @Column(name = "address_city")
    val city: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "address_state")
    val state: StateEnum,

    @OneToOne(mappedBy = "address")
    val patient: PatientEntity,

    @Column(name = "address_created_at")
    val createdAt: Date,

    @Column(name = "address_updated_at")
    val updatedAt: Date
)
