package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.constant.SexEnum
import com.dimedical.constant.StateEnum
import com.dimedical.entity.AddressEntity
import com.dimedical.entity.ContactEntity
import com.dimedical.entity.DocumentEntity
import com.dimedical.entity.PatientEntity
import com.dimedical.service.impl.PatientServiceImpl
import com.dimedical.util.extension.EntityToDTOExtension.toDTO
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.File
import java.nio.file.Files
import java.util.Date

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: PatientServiceImpl? = null

    @Test
    fun createPatientTest() {
        val request = getJson("request-patient.json")

        `when`(service?.addPatient(anyObject())).thenReturn(getPatient().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .post(APIConstant.BASE_API + APIConstant.POST_PATIENT)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun findDoctorTest() {
        `when`(service?.findPatient(1L)).thenReturn(getPatient().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .get(APIConstant.BASE_API + APIConstant.GET_PATIENT, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun updateUserTest() {
        val request = getJson("request-patient.json")

        `when`(service?.updatePatient(1L, getPatient())).thenReturn(getPatient().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .patch(APIConstant.BASE_API + APIConstant.PATCH_PATIENT, 1)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deleteUserTest() {
        doNothing().`when`(service)!!.deletePatient(1L)

        mvc!!.perform(
            MockMvcRequestBuilders
                .delete(APIConstant.BASE_API + APIConstant.DELETE_PATIENT, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
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

    fun getJson(fileName: String): String {
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(fileName).file)
        return String(Files.readAllBytes(file.toPath()))
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}
