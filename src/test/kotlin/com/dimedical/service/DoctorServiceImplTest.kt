package com.dimedical.service

import com.dimedical.constant.StateEnum
import com.dimedical.entity.DoctorEntity
import com.dimedical.repository.DoctorRepository
import com.dimedical.service.impl.DoctorServiceImpl
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
class DoctorServiceImplTest {

    @MockBean
    private val doctorRepository: DoctorRepository? = null

    @Autowired
    private val doctorService: DoctorServiceImpl? = null

    @Test
    fun addDoctorTestWithSuccess() {
        given<Optional<DoctorEntity>>(doctorRepository?.findByAdviceNumber(123456789))
            .willReturn(Optional.empty())

        given<DoctorEntity>(doctorRepository?.save(anyObject()))
            .willReturn(getDoctor())

        val response = doctorService!!.addDoctor(getDoctor())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun addDoctorTestWithError() {
        given<Optional<DoctorEntity>>(doctorRepository?.findByAdviceNumber(12345678))
            .willReturn(Optional.of(getDoctor()))

        Assertions.assertThrows(
            EntityExistsException::class.java
        ) { doctorService!!.addDoctor(getDoctor()) }
    }

    @Test
    fun findDoctorWithSuccessTest() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(1L))
            .willReturn(Optional.of(getDoctor()))

        val response = doctorService!!.findDoctor(1L)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
    }

    @Test
    fun findDoctorWithErrorTest() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { doctorService!!.findDoctor(2L) }
    }

    @Test
    fun updateDoctorWithSucessTest() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(1L))
            .willReturn(Optional.of(getDoctor()))

        given<Optional<DoctorEntity>>(doctorRepository?.findByAdviceNumber(12345678))
            .willReturn(Optional.of(getDoctor()))

        given<DoctorEntity>(doctorRepository?.save(anyObject()))
            .willReturn(getDoctorUpdate())

        val response = doctorService!!.updateDoctor(1L, getDoctor())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.id, 1L)
        Assertions.assertEquals(response.name, "Leslie Possidonio Almeida")
    }

    @Test
    fun updateDoctorWithDoctorNotExistsError() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { doctorService!!.updateDoctor(2L, getDoctor()) }
    }

    @Test
    fun updateDoctorWithPAdviceNumberExistsError() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(1L))
            .willReturn(Optional.of(getDoctor()))

        given<Optional<DoctorEntity>>(doctorRepository?.findByAdviceNumber(12345678))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityExistsException::class.java
        ) { doctorService!!.updateDoctor(1L, getDoctor()) }
    }

    @Test
    fun deleteDoctorWithSuccessTest() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(1L))
            .willReturn(Optional.of(getDoctor()))

        doNothing().`when`(doctorRepository)!!.delete(anyObject())

        doctorService!!.deleteDoctor(1L)

        verify(doctorRepository, times(1))!!.delete(anyObject())
    }

    @Test
    fun deleteDoctorWithDoctorNotExistsErrorTest() {
        given<Optional<DoctorEntity>>(doctorRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { doctorService!!.deleteDoctor(2L) }
    }

    private fun getDoctor() = DoctorEntity(
        1L,
        "Leslie Possidonio",
        12345678,
        StateEnum.SP,
        "CRM",
        Date()
    )

    private fun getDoctorUpdate() = DoctorEntity(
        1L,
        "Leslie Possidonio Almeida",
        12345678,
        StateEnum.SP,
        "CRM",
        Date()
    )

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}
