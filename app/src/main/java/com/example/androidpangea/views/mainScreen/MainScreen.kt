package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpangea.R
import com.example.androidpangea.navigation.BottomNavItem
import com.example.androidpangea.navigation.BottomNavigationBar
import com.example.androidpangea.navigation.BottomNavigationItem
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.navigation.Screen
import com.example.androidpangea.views.mapScreen.MapItemManager
import com.example.androidpangea.views.mapScreen.MapState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "PotentialBehaviorOverride")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: MapState,
    setupClusterManager: (Context, GoogleMap) -> MapItemManager,
    calculateZoneViewCenter: () -> LatLngBounds,
) {
    val screens = listOf(
        NavigationItem.Main.route,
        NavigationItem.User.route,
        NavigationItem.Camera.route,
        NavigationItem.Explore.route,
        "${NavigationItem.User.route}/{userid}",
        NavigationItem.SignUp.route,
        NavigationItem.SignIn.route
    )
    val bottomSheet = rememberModalBottomSheetState()

    var isBottomSheetOpened by remember {
        mutableStateOf(false)
    }

    val mapProperties = MapProperties(isMyLocationEnabled = state.lastKnownLocation != null)
    val cameraPositionState = rememberCameraPositionState()

val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController) }) {
//        if (isBottomSheetOpened) {
//            ModalBottomSheet(
//                sheetState = bottomSheet,
//                onDismissRequest = {
//                    isBottomSheetOpened = false
//                }
//            ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 24.dp)
//                    .padding(horizontal = 24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Options",
//                    modifier = Modifier.fillMaxWidth(),
//                    style = MaterialTheme.typography.headlineMedium,
//                    textAlign = TextAlign.Center
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Unfollow",
//                    modifier = Modifier.fillMaxWidth(),
//                    style = MaterialTheme.typography.bodyLarge,
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Go to profile",
//                    modifier = Modifier.fillMaxWidth(),
//                    style = MaterialTheme.typography.bodyLarge,
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Row(Modifier.fillMaxWidth()) {
//                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Share",
//                        modifier = Modifier.fillMaxWidth(),
//                        style = MaterialTheme.typography.bodyLarge,
//                    )
//                }
//            }
//        }
//    }





        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) { //    CircularImage(imageUrl = user?.profileImage ?: "")
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
                            calculateZoneViewCenter(), 0
                        ),
                    )
                }
            }
        }
    }
}


