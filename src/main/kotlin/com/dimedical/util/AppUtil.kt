package com.dimedical.util

object AppUtil {

    fun normalizeField(field: String): String {
        val regex = Regex("[^A-Za-z0-9]")
        return regex.replace(field, "")
    }
}
