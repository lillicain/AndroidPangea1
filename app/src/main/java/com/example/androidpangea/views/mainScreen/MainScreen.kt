package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.BottomNavigationBar
import com.example.androidpangea.views.mapScreen.MapItemManager
import com.example.androidpangea.views.mapScreen.MapState
import com.example.androidpangea.views.subviews.CircularImage
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
    modifier: Modifier = Modifier,
    user: User,
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> MapItemManager,
    calculateZoneViewCenter: () -> LatLngBounds,

//        viewModel: MainViewModel,
//    navController: NavController,
) {

//    val userState = viewModel.users.collectAsState()
//    val postsState = viewModel.posts.collectAsState()
//    val bottomSheet = rememberModalBottomSheetState()
    var isBottomSheetOpened by remember {
        mutableStateOf(false)
    }

    val mapProperties = MapProperties(isMyLocationEnabled = state.lastKnownLocation != null)
    val cameraPositionState = rememberCameraPositionState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),


        ) {
Column(
    modifier = Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
) {
//    CircularImage(imageUrl = user?.profileImage ?: "")
}
//        Text(text = user?.username ?: "")


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
                .padding(top = 200.dp)
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

        LaunchedEffect(state.mapItems) {
            if (state.mapItems.isNotEmpty()) {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngBounds(
                        calculateZoneViewCenter(),
                        0
                    ),
                )
            }
        }
    }
}

