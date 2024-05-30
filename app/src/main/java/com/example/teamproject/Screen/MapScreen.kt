package com.example.teamproject.Screen

import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.teamproject.Item.LocationItem
import com.example.teamproject.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.CircleOverlay
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

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(navController: NavHostController, location: List<LocationItem>) {
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
                )
            }
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun SetMarker(latitude: Double, longitude: Double, navController: NavController, route: String) {
    Marker(
        state = MarkerState(position = LatLng(latitude, longitude)),
        icon = OverlayImage.fromResource(R.drawable.red_marker), width = 30.dp,
        height = 30.dp,
        onClick = {
            navController.navigate(route)
            true
        }

    )
}

@Composable
fun DrawMarker(currLocation: LatLng, location: List<LocationItem>, navController: NavController) {
    // 사용자의 현재 위치
    val userLocation = currLocation

    val markerLocation = mutableListOf<Marking>()
    location.forEach {
        markerLocation.add(Marking(it.latitiude, it.longtitude,it.route))
    }

    CircleOverlay(
        center = LatLng(userLocation.latitude, userLocation.longitude),
        Color.Red.copy(alpha = 0.3F),
        10.0
    )

    markerLocation.forEach { it ->
        SetMarker(it.latitude, it.longitude, navController,it.route)
    }

}


// 두 위치 간의 거리를 계산
fun calculateDistance(location1: LatLng, location2: LatLng): Float {
    val results = FloatArray(1)
    Location.distanceBetween(
        location1.latitude, location1.longitude,
        location2.latitude, location2.longitude,
        results
    )
    return results[0]
}

data class Marking ( val latitude: Double,val longitude: Double , val route : String)

//private const val MAX_DISTANCE_THRESHOLD = 150f // 사용자 반경

/*LatLng(37.5442615, 127.0760717), // 경영관 ->레스티오
    LatLng(37.5419226, 127.0737408), // 상허기념도서관 -> 레스티오 , 구시아푸드
    LatLng(37.5403664, 127.0743614), // 동물생명과학관 -> 레스티오
    LatLng(37.5396663, 127.0732309), // 산학협동관-> 레스티오
    LatLng(37.5418772, 127.0782087), // 학생회관 - >구시아 푸드 , 1층 학생식당
    LatLng(37.541635, 127.0787904),  // 공학관 -> 레스티오*/
