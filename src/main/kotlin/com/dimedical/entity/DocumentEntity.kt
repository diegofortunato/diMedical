package com.dimedical.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "document_tb",
    uniqueConstraints =
    [UniqueConstraint(columnNames = ["document_cpf", "document_rg"])]
)
data class DocumentEntity(

    @Id
    @Column(name = "document_cpf")
    var cpf: String,

    @Column(name = "document_rg")
    var rg: String,

    @OneToOne(mappedBy = "document")
    val patient: PatientEntity?,

    @Column(name = "document_created_at")
    val createdAt: Date,
)
