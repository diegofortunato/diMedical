package com.dimedical.entity

import com.dimedical.constant.SexEnum
import java.util.Date
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
    var name: String,

    @Column(name = "patient_birth_date")
    val birthDate: Date,

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_sex")
    var sex: SexEnum,

    @Column(name = "patient_mothers_name")
    var mothersName: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    var address: AddressEntity,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
    var contact: ContactEntity,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "document_cpf", referencedColumnName = "document_cpf")
    var document: DocumentEntity,

    @Column(name = "patient_created_at")
    val createdAt: Date
)
