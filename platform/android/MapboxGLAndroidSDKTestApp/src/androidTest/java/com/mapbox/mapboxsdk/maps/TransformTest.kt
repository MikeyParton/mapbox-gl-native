package com.mapbox.mapboxsdk.maps

import android.support.test.espresso.UiController
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.testapp.action.MapboxMapAction.invoke
import com.mapbox.mapboxsdk.testapp.activity.BaseActivityTest
import com.mapbox.mapboxsdk.testapp.activity.maplayout.SimpleMapActivity
import org.junit.Assert.assertEquals
import org.junit.Test


class TransformTest: BaseActivityTest() {

    override fun getActivityClass(): Class<*> = SimpleMapActivity::class.java

    companion object {
        val initialCameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(12.0,12.0), 12.0)!!
        val scrollByCameraUpdate = CameraUpdateFactory.scrollBy(400.0f,0.0f)!!
    }

    @Test
    fun scrollByWithPadding() {
        validateTestSetup()
        invoke(mapboxMap) { uiController: UiController, mapboxMap: MapboxMap ->
            mapboxMap.moveCamera(initialCameraUpdate)
            mapboxMap.moveCamera(scrollByCameraUpdate)
            val expectedCameraPosition = mapboxMap.cameraPosition

            mapboxMap.moveCamera(initialCameraUpdate)
            mapboxMap.setPadding(250,250,0,0)
            mapboxMap.moveCamera(scrollByCameraUpdate)
            val actualCameraPosition = mapboxMap.cameraPosition

            assertEquals("Camera position should match", expectedCameraPosition, actualCameraPosition)
        }
    }

}