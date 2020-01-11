package com.example.learncamera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsHandler(val activity: Activity) {

    private val REQUEST_CODE = 42

    fun havePermission() = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
        activity,
        Manifest.permission.CAMERA
    )

    fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_CODE
        )
    }

    fun checkPermissionRequestResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) =
        (REQUEST_CODE == requestCode)
                && (Manifest.permission.CAMERA == permissions[0])
                && grantResults.isNotEmpty()
                && (PackageManager.PERMISSION_GRANTED == grantResults[0])

}