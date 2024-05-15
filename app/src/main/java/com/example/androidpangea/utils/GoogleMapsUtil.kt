package com.example.androidpangea.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

fun List<LatLng>.getCenterOfPolygon(): LatLngBounds {
    val centerBuilder: LatLngBounds.Builder = LatLngBounds.builder()
    forEach { centerBuilder.include(LatLng(it.latitude, it.longitude)) }
    return centerBuilder.build()
}

private data class CameraViewCoordinates(
    val yMax: Double,
    val yMin: Double,
    val xMax: Double,
    val xMin: Double
)

fun List<LatLng>.calculateCameraViewPoints(pctView: Double = .25): List<LatLng> {
    val cordMax = findMaxMins()
    val dy = cordMax.yMax - cordMax.yMin
    val dx = cordMax.xMax - cordMax.xMin
    val yT = (dy * pctView) + cordMax.yMax
    val yB = cordMax.yMin - (dy * pctView)
    val xR = (dx * pctView) + cordMax.xMax
    val xL = cordMax.xMin - (dx * pctView)
    return listOf(
        LatLng(cordMax.xMax, yT),
        LatLng(cordMax.xMin, yB),
        LatLng(xR, cordMax.yMax),
        LatLng(xL, cordMax.yMin)
    )
}

private fun List<LatLng>.findMaxMins(): CameraViewCoordinates {
    check(size > 0) { "Cannot calculate the view coordinates of nothing" }
    var viewCord: CameraViewCoordinates? = null
    for(point in this) {
        viewCord = CameraViewCoordinates(
            yMax = viewCord?.yMax?.let { yMax ->
                if (point.longitude > yMax) {
                    point.longitude
                } else {
                    yMax
                }
            } ?: point.longitude,
            yMin = viewCord?.yMin?.let { yMin->
                if (point.longitude < yMin) {
                    point.longitude
                } else {
                    yMin
                }
            } ?: point.longitude,
            xMax = viewCord?.xMax?.let { xMax->
                if (point.latitude > xMax) {
                    point.latitude
                } else {
                    xMax
                }
            } ?: point.latitude,
            xMin = viewCord?.xMin?.let { xMin->
                if (point.latitude < xMin) {
                    point.latitude
                } else {
                    xMin
                }
            } ?: point.latitude,
        )
    }
    return viewCord ?: throw IllegalStateException("Coordinates cannot be null")
}