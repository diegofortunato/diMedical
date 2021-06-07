package com.dimedical.entity

import com.dimedical.constant.SexEnum
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "patient_tb")
data class PatientEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    var id: Long,

    @Column(name = "patient_name")
    val name: String,

    @Column(name = "patient_birth_date")
    val birthDate: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_sex")
    val sex: SexEnum,

    @Column(name = "patient_mothers_name")
    val mothersName: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    val address: AddressEntity,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    val contact: ContactEntity,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    val document: DocumentEntity,

    @Column(name = "patient_created_at")
    val createdAt: Date,

    @Column(name = "patient_updated_at")
    val updatedAt: Date
)
