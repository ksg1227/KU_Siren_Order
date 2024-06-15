package com.example.teamproject.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.teamproject.Component.CustomAlertDialog
import com.example.teamproject.Item.CustomAlertDialogState
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.R
import com.example.teamproject.navigation.Routes
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(navController: NavHostController, location: List<LocationItem>) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    val customAlertDialogState: MutableState<CustomAlertDialogState> =
        mutableStateOf(
            CustomAlertDialogState()
        )

    fun showCustomAlertDialog(
    ) {
        showDialog = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val markerState = remember {
            mutableStateOf<LatLng?>(null)
        }
        NaverMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = rememberCameraPositionState(),
            locationSource = rememberFusedLocationSource(),
            properties = MapProperties(
                locationTrackingMode = LocationTrackingMode.Follow
            ),
            uiSettings = MapUiSettings(
                isLocationButtonEnabled = true,
            ),
            onLocationChange = { location ->
                markerState.value = LatLng(location.latitude, location.longitude)
            },

            ) {
            markerState.value?.let {
                DrawMarker(
                    currLocation = it,
                    location = location,
                    navController = navController
                ) { showCustomAlertDialog() }
            }
        }
        if (showDialog) {
            CustomAlertDialog(
                title = customAlertDialogState.value.title,
                onClickToFloor = {
                    showDialog = false
                    navController.navigate(Routes.LibraryGusia.route)
                },
                onClickToBottom = {
                    showDialog = false
                    navController.navigate(Routes.LibraryGusia.route)
                },
                onClickCancel = { showDialog = false }
            )
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun SetMarker(
    latitude: Double,
    longitude: Double,
    navController: NavController,
    route: String,
    showCustomAlertDialog: () -> Unit
) {
    Marker(
        state = MarkerState(position = LatLng(latitude, longitude)),
        icon = OverlayImage.fromResource(R.drawable.red_marker), width = 30.dp,
        height = 30.dp,
        onClick = {
            if (latitude == 37.5418772 && longitude == 127.0782087) {
                showCustomAlertDialog()
            } else {
                navController.navigate(route)
            }
            true
        }

    )
}

@Composable
fun DrawMarker(
    currLocation: LatLng,
    location: List<LocationItem>,
    navController: NavController,
    showCustomAlertDialog: () -> Unit
) {
    // 사용자의 현재 위치

    val markerLocation = mutableListOf<Marking>()
    location.forEach {
        markerLocation.add(Marking(it.latitiude, it.longtitude, it.route))
    }

    markerLocation.forEach {
        SetMarker(it.latitude, it.longitude, navController, it.route, showCustomAlertDialog)
    }

}


data class Marking(val latitude: Double, val longitude: Double, val route: String)

