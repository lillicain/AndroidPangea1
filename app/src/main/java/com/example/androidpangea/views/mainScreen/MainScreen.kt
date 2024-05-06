package com.example.androidpangea.views.mainScreen

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidpangea.R
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.BottomNavigationBar
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.views.mapScreen.MapItemManager
import com.example.androidpangea.views.mapScreen.MapState
import com.example.androidpangea.views.subviews.CenterCircularProgressBar
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

    val bottomSheet = rememberModalBottomSheetState()

    var isBottomSheetOpened by remember {
        mutableStateOf(false)
    }

    val mapProperties = MapProperties(isMyLocationEnabled = state.lastKnownLocation != null)
    val cameraPositionState = rememberCameraPositionState()


    if (isBottomSheetOpened) {
        ModalBottomSheet(
            sheetState = bottomSheet,
            onDismissRequest = {
                isBottomSheetOpened = false
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Options",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Unfollow",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Go to profile",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "share")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Share",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }

//    when (val state = userState.value) {
//        is BaseState.Failed -> {
//            when (state.error) {
//                is Failure.Unknown -> {
//                    Button(onClick = {
//                        homeViewModel.getUsers()
//                        homeViewModel.getPosts()
//                        homeViewModel.getStories()
//                    }) {
//                        Text("${state.error.error}\nRetry")
//                    }
//                }
//            }
//        }



            Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { navController.navigate(NavigationItem.User.route) }) {
                        Icon(
                            painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            Modifier.padding(8.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate(NavigationItem.Camera.route) }) {
                        Icon(
                            painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            Modifier.padding(8.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate(NavigationItem.Explore.route) }) {
                        Icon(
                            painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            Modifier.padding(8.dp)
                        )
                    }

                    //              IconButton(onClick = { navController.navigate(NavigationItem.User.route) }) {
                    //                  Icon(imageVector = Icons.Filled.Image, contentDescription = "Camera")
                    //              }
                }
            }


            ) {

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

