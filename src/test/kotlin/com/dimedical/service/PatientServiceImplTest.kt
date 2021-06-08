package com.dimedical.service

import com.dimedical.constant.SexEnum
import com.dimedical.constant.StateEnum
import com.dimedical.entity.AddressEntity
import com.dimedical.entity.ContactEntity
import com.dimedical.entity.DocumentEntity
import com.dimedical.entity.PatientEntity
import com.dimedical.repository.PatientRepository
import com.dimedical.service.impl.PatientServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.Date
import java.util.Optional
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@SpringBootTest
@AutoConfigureMockMvc
class PatientServiceImplTest {

    @MockBean
    private val patientRepository: PatientRepository? = null

    @Autowired
    private val patientService: PatientServiceImpl? = null

    @Test
    fun addPatientTestWithSuccess() {
        given(patientRepository!!.findByDocumentCpf("45474132822"))
            .willReturn(Optional.empty())

        given(patientRepository.save(anyObject()))
            .willReturn(getPatient())

        val response = patientService!!.addPatient(getPatient())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun addPatientTestWithError() {
        given<Optional<PatientEntity>>(patientRepository?.findByDocumentCpf("45474132824"))
            .willReturn(Optional.of(getPatient()))

        Assertions.assertThrows(
            EntityExistsException::class.java
        ) { patientService!!.addPatient(getPatient()) }
    }

    @Test
    fun findPatientWithSuccessTest() {
        given<Optional<PatientEntity>>(patientRepository?.findById(1L))
            .willReturn(Optional.of(getPatient()))

        val response = patientService!!.findPatient(1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun findPatientWithErrorTest() {
        given<Optional<PatientEntity>>(patientRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { patientService!!.findPatient(2L) }
    }

    @Test
    fun updatePatientWithSucessTest() {
        given<Optional<PatientEntity>>(patientRepository?.findById(1L))
            .willReturn(Optional.of(getPatient()))

        given<Optional<PatientEntity>>(patientRepository?.findByDocumentCpf("45474132824"))
            .willReturn(Optional.of(getPatient()))

        given<PatientEntity>(patientRepository?.save(anyObject()))
            .willReturn(getPatientUpdated())

        val response = patientService!!.updatePatient(1L, getPatient())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
        Assertions.assertEquals(response.name, "Diego Fortunato Candido")
    }

    @Test
    fun updatePatientWithPatientNotExistsError() {
        given<Optional<PatientEntity>>(patientRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { patientService!!.updatePatient(2L, getPatient()) }
    }

    @Test
    fun updatePatientWithCPFExistsError() {
        given<Optional<PatientEntity>>(patientRepository?.findById(1L))
            .willReturn(Optional.of(getPatient()))

        given<Optional<PatientEntity>>(patientRepository?.findByDocumentCpf("45474132822"))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityExistsException::class.java
        ) { patientService!!.updatePatient(1L, getPatient()) }
    }

    @Test
    fun deletePatientWithSuccessTest() {
        given<Optional<PatientEntity>>(patientRepository?.findById(1L))
            .willReturn(Optional.of(getPatient()))

        doNothing().`when`(patientRepository)!!.delete(anyObject())

        patientService!!.deletePatient(1L)

        verify(patientRepository, times(1))!!.delete(anyObject())
    }

    @Test
    fun deletePatientWithPatientNotExistsErrorTest() {
        given<Optional<PatientEntity>>(patientRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { patientService!!.deletePatient(2L) }
    }

    private fun getPatient() = PatientEntity(
        1L,
        "Diego Fortunato",
        Date(),
        SexEnum.MASCULINO,
        "Carla Cristina",
        AddressEntity(
            1L,
            "Rua Itapolis",
            1325,
            "01245000",
            "Pacaembu",
            "Sao Paulo",
            StateEnum.SP,
            null,
            Date()
        ),
        ContactEntity(
            1L,
            "(19)9.9477-8827",
            "diego@gmail.com",
            null,
            Date()
        ),
        DocumentEntity(
            "45474132824",
            "387963595",
            null,
            Date()
        ),
        Date()
    )

    private fun getPatientUpdated() = PatientEntity(
        1L,
        "Diego Fortunato Candido",
        Date(),
        SexEnum.MASCULINO,
        "Carla Cristina",
        AddressEntity(
            1L,
            "Rua Itapolis",
            1325,
            "01245000",
            "Pacaembu",
            "Sao Paulo",
            StateEnum.SP,
            null,
            Date()
        ),
        ContactEntity(
            1L,
            "(19)9.9477-8827",
            "diego@gmail.com",
            null,
            Date()
        ),
        DocumentEntity(
            "45474132824",
            "387963595",
            null,
            Date()
        ),
        Date()
    )

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}
