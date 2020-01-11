package com.example.learncamera

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.fotoapparat.Fotoapparat

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val permissionsHandler = PermissionsHandler(this)

    private var permissionGranted = false

    private lateinit var fotoapparat: Fotoapparat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        permissionGranted = permissionsHandler.havePermission()
        if(permissionGranted)
            cameraView.visibility = View.VISIBLE
        else
            permissionsHandler.requestPermission()

        fotoapparat = Fotoapparat(
            context = this,
            view = cameraView
        )

    }

    override fun onStart() {
        super.onStart()
        if (permissionGranted) {
            fotoapparat.start()
        }
    }

    override fun onStop() {
        super.onStop()
        if (permissionGranted) {
            fotoapparat.stop()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionsHandler.checkPermissionRequestResult(requestCode, permissions, grantResults)) {
            permissionGranted = true
            fotoapparat.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
