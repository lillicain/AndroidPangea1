package com.example.androidpangea.views.subviews

import android.graphics.Bitmap
import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

suspend fun CameraPositionState.centerOnLocation(location: Location) = animate(update = CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15f))

data class CameraState(val capturedImage: Bitmap? = null)