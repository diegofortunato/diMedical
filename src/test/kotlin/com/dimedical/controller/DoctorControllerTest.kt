package com.dimedical.controller

import com.dimedical.constant.APIConstant
import com.dimedical.constant.StateEnum
import com.dimedical.entity.DoctorEntity
import com.dimedical.service.impl.DoctorServiceImpl
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
class DoctorControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val service: DoctorServiceImpl? = null

    @Test
    fun createDoctorTest() {
        val request = getJson("request-doctor.json")

        `when`(service?.addDoctor(anyObject())).thenReturn(getDoctor().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .post(APIConstant.BASE_API + APIConstant.POST_DOCTOR)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun findDoctorTest() {
        `when`(service?.findDoctor(1L)).thenReturn(getDoctor().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .get(APIConstant.BASE_API + APIConstant.GET_DOCTOR, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun updateUserTest() {
        val request = getJson("request-doctor.json")

        `when`(service?.updateDoctor(1L, getDoctor())).thenReturn(getDoctor().toDTO())

        mvc!!.perform(
            MockMvcRequestBuilders
                .patch(APIConstant.BASE_API + APIConstant.PATCH_DOCTOR, 1)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deleteUserTest() {
        doNothing().`when`(service)!!.deleteDoctor(1L)

        mvc!!.perform(
            MockMvcRequestBuilders
                .delete(APIConstant.BASE_API + APIConstant.DELETE_DOCTOR, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    private fun getDoctor() = DoctorEntity(
        1L,
        "Leslie Possidonio",
        12345678,
        StateEnum.SP,
        "CRM",
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
