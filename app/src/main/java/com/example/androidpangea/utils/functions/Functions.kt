package com.example.androidpangea.utils.functions

import androidx.compose.ui.text.input.TextFieldValue
import com.example.androidpangea.models.toUser
import com.example.androidpangea.utils.Utils
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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


fun parseEventDate(day: String?, month: String?, year: String?): String? {
    return try {
        val isBC = year?.startsWith('-') == true
        val absYear = year?.removePrefix("-")?.toIntOrNull() ?: 0

        val formattedYear = if (isBC) {
            "BC $absYear"
        } else {
            absYear.toString()
        }
        return "$day/$month/$formattedYear"
    } catch (e: ParseException) {
        null
    }
}

fun getCurrentTime(): String {
    val currentTime = Calendar.getInstance().time
    val pattern = "dd/MM/yyyy - HH:mm"
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(currentTime)
}
