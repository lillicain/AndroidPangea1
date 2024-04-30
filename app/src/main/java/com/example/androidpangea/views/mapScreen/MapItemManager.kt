package com.example.androidpangea.views.mapScreen

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.MarkerManager

class MapItemManager(
    context: Context,
    googleMap: GoogleMap,
): ClusterManager<MapItem>(context, googleMap, MarkerManager(googleMap))