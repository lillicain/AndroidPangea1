package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.androidpangea.views.mapScreen.MapItemManager
import com.example.androidpangea.views.mapScreen.MapState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "PotentialBehaviorOverride")
@Composable
fun MainScreen(
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> MapItemManager,
    calculateZoneViewCenter: () -> LatLngBounds,
) {

    val mapProperties = MapProperties(isMyLocationEnabled = state.lastKnownLocation != null)
    val cameraPositionState = rememberCameraPositionState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),


        ) {

        Text(text = "User")

        Box(
            modifier = Modifier.fillMaxSize().padding(50.dp).padding(top = 200.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = mapProperties,
                cameraPositionState = cameraPositionState
            ) {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                MapEffect(state.mapItems) { map ->
                    if (state.mapItems.isNotEmpty()) {
                        val clusterManager = setupClusterManager(context, map)
                        map.setOnCameraIdleListener(clusterManager)
                        map.setOnMarkerClickListener(clusterManager)
                        state.mapItems.forEach { clusterItem ->
                            map.addPolygon(clusterItem.polygonOptions)
                        }
                        map.setOnMapLoadedCallback {
                            if (state.mapItems.isNotEmpty()) {
                                scope.launch {
                                    cameraPositionState.animate(
                                        update = CameraUpdateFactory.newLatLngBounds(
                                            calculateZoneViewCenter(), 0
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }


                MarkerInfoWindow(
                    state = rememberMarkerState(position = LatLng(37.09, 113.59)),
                    snippet = "Some stuff",
                    onClick = {
                        System.out.println("Cannot be clicked")
                        true
                    },
                    draggable = true
                )
            }
        }

        //    // Center camera to include all the Zones.
        //    LaunchedEffect(state.clusterItems) {
        //        if (state.clusterItems.isNotEmpty()) {
        //            cameraPositionState.animate(
        //                update = CameraUpdateFactory.newLatLngBounds(
        //                    calculateZoneViewCenter(),
        //                    0
        //                ),
        //            )
        //        }
        //    }
    }

}

    suspend fun CameraPositionState.centerOnLocation(
        location: Location
    ) = animate(
        update = CameraUpdateFactory.newLatLngZoom(
            LatLng(location.latitude, location.longitude), 15f
        ),
    )
