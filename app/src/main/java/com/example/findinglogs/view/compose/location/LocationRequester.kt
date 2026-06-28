package com.example.findinglogs.view.compose.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.findinglogs.viewmodel.MainViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@Composable
fun RequestCurrentLocation(viewModel: MainViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) fetchCurrentLocation(context, viewModel)
    }

    LaunchedEffect(Unit) {
        val granted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (granted) fetchCurrentLocation(context, viewModel)
        else launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

@SuppressLint("MissingPermission")
private fun fetchCurrentLocation(context: Context, viewModel: MainViewModel) {
    val client = LocationServices.getFusedLocationProviderClient(context)
    client.lastLocation.addOnSuccessListener { last ->
        if (last != null) {
            viewModel.setCurrentLocation("${last.latitude},${last.longitude}")
        }
    }
    val request = com.google.android.gms.location.LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, 1000
    ).setMaxUpdates(1).build()

    client.requestLocationUpdates(
        request,
        object : com.google.android.gms.location.LocationCallback() {
            override fun onLocationResult(result: com.google.android.gms.location.LocationResult) {
                result.lastLocation?.let {
                    viewModel.setCurrentLocation("${it.latitude},${it.longitude}")
                }
                client.removeLocationUpdates(this)
            }
        },
        android.os.Looper.getMainLooper()
    )
}