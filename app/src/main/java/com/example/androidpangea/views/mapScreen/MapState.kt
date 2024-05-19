package com.example.androidpangea.views.mapScreen

import android.location.Location

data class MapState(
    val lastKnownLocation: Location?,
    val mapItems: List<MapItem>,
)