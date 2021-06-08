package com.dimedical.util

import java.text.Normalizer

object AppUtil {

    fun normalizeField(field: String): String {
        return Normalizer.normalize(field, Normalizer.Form.NFD).replace("[^\\p{ASCII}]", "")
    }
}
