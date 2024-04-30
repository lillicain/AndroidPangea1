package com.example.androidpangea.views.mapScreen

import com.example.androidpangea.extensions.getCenterOfPolygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.maps.android.clustering.ClusterItem

data class MapItem(
    val id: String,
    private val title: String,
    private val snippet: String,
    val polygonOptions: PolygonOptions
) : ClusterItem {

    override fun getSnippet() = snippet
    override fun getTitle() = title
    override fun getPosition() = polygonOptions.points.getCenterOfPolygon().center
}