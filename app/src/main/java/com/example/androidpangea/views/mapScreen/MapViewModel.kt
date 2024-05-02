package com.example.androidpangea.views.mapScreen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androidpangea.extensions.calculateCameraViewPoints
import com.example.androidpangea.extensions.getCenterOfPolygon
import com.example.androidpangea.views.mapScreen.MapState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.ktx.model.circleOptions
import com.google.maps.android.ktx.model.polygonOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(): ViewModel() {

    val state: MutableState<MapState> = mutableStateOf(
        MapState(
            lastKnownLocation = null,
            mapItems = listOf(
                MapItem(
                    id = "1",
                    title = "School",
                    snippet = "This is Zone 1.",
                    polygonOptions = polygonOptions {
//                        add(LatLng(37.09, 113.57))
//                        add(LatLng(37.098, 113.58))
//                        add(LatLng(37.094, 113.591))
//                        add(LatLng(37.01, 113.59))

                        add(LatLng(39.105, -122.524))
                        add(LatLng(39.101, -122.529))
                        add(LatLng(39.092, -122.501))
                        add(LatLng(39.1, -122.506))

                        fillColor(POLYGON_FILL_COLOR)
                    }
                ),
                MapItem(
                    id = "2",
                    title = "Test",
                    snippet = "This is a test area.",
                    polygonOptions = polygonOptions {

                        add(LatLng(39.105, -122.524))
                        add(LatLng(39.101, -122.529))
                        add(LatLng(39.092, -122.501))
                        add(LatLng(39.1, -122.506))

//                        add(LatLng(37.11, 113.36))
//                        add(LatLng(37.123, 113.373))
//                        add(LatLng(37.111, 113.37))

                        fillColor(POLYGON_FILL_COLOR)
                    }
                )
            )
        )
    )

    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = state.value.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (_: SecurityException) {

        }
    }

    fun setupClusterManager(
        context: Context,
        map: GoogleMap,
    ): MapItemManager {
        val clusterManager = MapItemManager(context, map)
        clusterManager.addItems(state.value.mapItems)
        return clusterManager
    }

    fun calculateZoneLatLngBounds(): LatLngBounds {
        val latLngs = state.value.mapItems.map { it.polygonOptions }
            .map { it.points.map { LatLng(it.latitude, it.longitude) } }.flatten()
        return latLngs.calculateCameraViewPoints().getCenterOfPolygon()
    }


    companion object {
        private val POLYGON_FILL_COLOR = Color.parseColor("#ABF44336")
    }
}

