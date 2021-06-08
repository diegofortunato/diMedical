package com.dimedical.service

import com.dimedical.constant.SexEnum
import com.dimedical.constant.StateEnum
import com.dimedical.entity.AddressEntity
import com.dimedical.entity.ContactEntity
import com.dimedical.entity.DoctorEntity
import com.dimedical.entity.DocumentEntity
import com.dimedical.entity.ExamEntity
import com.dimedical.entity.MedicalRequestEntity
import com.dimedical.entity.PatientEntity
import com.dimedical.repository.ExamRepository
import com.dimedical.repository.MedicalRequestRepository
import com.dimedical.service.impl.DoctorServiceImpl
import com.dimedical.service.impl.MedicalRequestServiceImpl
import com.dimedical.service.impl.PatientServiceImpl
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.Date
import java.util.Optional
import javax.persistence.EntityNotFoundException

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRequestServiceImplTest {

    @MockBean
    private val medicalRequestRepository: MedicalRequestRepository? = null

    @MockBean
    private val examRepository: ExamRepository? = null

    @Mock
    private val patientService: PatientServiceImpl? = null

    @Mock
    private val doctorService: DoctorServiceImpl? = null

    @Autowired
    private val medicalRequestService: MedicalRequestServiceImpl? = null

    @Test
    fun addMedicalRequestWithSuccessTest() {
        `when`(patientService!!.findPatient(1L)).thenReturn(getPatient().toDTO())

        `when`(doctorService!!.findDoctor(1L)).thenReturn(getDoctor().toDTO())

        given<MedicalRequestEntity>(medicalRequestRepository?.save(anyObject()))
            .willReturn(getMedicalRequest())

        given<ExamEntity>(examRepository?.save(anyObject()))
            .willReturn(getExamsList()[0])

        val service = MedicalRequestServiceImpl(medicalRequestRepository!!, examRepository!!, patientService, doctorService)

        val response = service.addMedicalRequest(getMedicalRequest(), 1L, 1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun findMedicalRequestWithSuccessTest() {
        given<Optional<MedicalRequestEntity>>(medicalRequestRepository?.findById(1L))
            .willReturn(Optional.of(getMedicalRequest()))

        val response = medicalRequestService!!.findMedicalRequest(1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun findMedicalRequestWithMedicalNotExistsErrorTest() {
        given<Optional<MedicalRequestEntity>>(medicalRequestRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { medicalRequestService!!.findMedicalRequest(2L) }
    }

    @Test
    fun findAllMedicalRequestWithSuccessTest() {
        val medicalRequestList = listOf(getMedicalRequest())
        val paging = PageRequest.of(0, 1, Sort.by("id"))

        given<Page<MedicalRequestEntity>>(medicalRequestRepository?.findAll(paging))
            .willReturn(PageImpl(medicalRequestList))

        val response = medicalRequestService!!.findAllMedicalRequest(paging)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.size, 1)
        Assertions.assertEquals(response.content[0].id, 1L)
    }

    private fun getMedicalRequest() = MedicalRequestEntity(
        1L,
        getPatient(),
        getDoctor(),
        getExamsList(),
        Date()
    )

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

    private fun getDoctor() = DoctorEntity(
        1L,
        "Leslie Possidonio",
        12345678,
        StateEnum.SP,
        "CRM",
        Date()
    )

    private fun getExamsList(): List<ExamEntity> {
        return listOf(ExamEntity(1L, "Exame de Sangue", Date(), null, Date()))
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}
