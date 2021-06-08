package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.constant.SexEnum
import com.dimedical.constant.StateEnum
import com.dimedical.entity.AddressEntity
import com.dimedical.entity.ContactEntity
import com.dimedical.entity.DoctorEntity
import com.dimedical.entity.DocumentEntity
import com.dimedical.entity.ExamEntity
import com.dimedical.entity.MedicalRequestEntity
import com.dimedical.entity.PatientEntity
import com.dimedical.service.impl.MedicalRequestServiceImpl
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.File
import java.nio.file.Files
import java.util.Date

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRequestControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: MedicalRequestServiceImpl? = null

    @Test
    fun createMedicalRequestTest() {
        val request = getJson("request-medical-request.json")

        `when`(service?.addMedicalRequest(getMedicalRequest(), 1L, 1L)).thenReturn(getMedicalRequest().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .post(APIConstant.BASE_API + APIConstant.POST_MEDICAL_REQUEST)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun findMedicalRequestByIDTest() {
        `when`(service?.findMedicalRequest(1L)).thenReturn(getMedicalRequest().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .get(APIConstant.BASE_API + APIConstant.GET_MEDICAL_REQUEST, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun findAllMedicalRequestest() {
        val paging = PageRequest.of(0, 10, Sort.by("id"))

        `when`(service?.findAllMedicalRequest(paging)).thenReturn(PageImpl(listOf(getMedicalRequest().toDTO())))

        mvc!!.perform(
            MockMvcRequestBuilders
                .get(APIConstant.BASE_API + APIConstant.GET_ALL_MEDICAL_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
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

    fun getJson(fileName: String): String {
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(fileName).file)
        return String(Files.readAllBytes(file.toPath()))
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}