package com.dimedical.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AppUtilTest {

    @Test
    fun normalizeFieldTest() {
        val result = AppUtil.normalizeField("454.741.328-24")

        Assertions.assertNotNull(result)
        Assertions.assertEquals(result, "45474132824")
    }
}
