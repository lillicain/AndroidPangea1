package com.example.androidpangea.utils.functions

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import com.google.android.gms.maps.model.LatLng

fun Double?.formatWithCommasForArea(): String {
    if (this == null) {
        return "Undefined"
    }
    val formattedValue = String.format("%,.2f", this)
    return if (formattedValue.endsWith(".00")) {
        formattedValue.substring(0, formattedValue.length - 3) + " km²"
    } else {
        "$formattedValue km²"
    }
}


fun Int?.formatWithCommasForPopulation(): String {
    return if (this == null) {
        "Undefined"
    } else {
        String.format("%,d", this)
    }
}

fun String.formatToUri() :String = if (this.contains(" ")) this.replace(" ", "_") else this

fun String.formatFromUri(): String = if (this.contains("_")) this.replace("_", " ") else this

fun List<Double>?.getLatLngFromRemote(): LatLng {
    return LatLng(this?.get(0) ?: 0.0, this?.get(1) ?: 0.0)
}

fun TextFieldValue.emailVerifier() : Boolean = !(this.text.contains("@") && this.text.contains(".com")) && this.text.isNotBlank()

fun TextFieldValue.passwordVerifier(): Boolean = !(this.text.length > 6 &&
        Regex("[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/_-]").containsMatchIn(this.text) &&
        Regex("[A-Z]").containsMatchIn(this.text) &&
        Regex("\\d").containsMatchIn(this.text)) && this.text.isNotBlank()
