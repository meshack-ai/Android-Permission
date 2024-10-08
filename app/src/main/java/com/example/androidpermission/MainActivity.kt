package com.example.androidpermission

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.Toast
import android.Manifest
import android.view.View


class MainActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Defining Buttons
        val storage: Button? = findViewById(R.id.storage)
        val camera: Button? = findViewById(R.id.camera)

        //onclick on buttons
        storage?.setOnClickListener {
            checkPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_PERMISSION_CODE
            )
        }


    camera?.setOnClickListener {
        checkPermission(Manifest.permission.CAMERA,
            CAMERA_PERMISSION_CODE)

    }

    }
//Function to check  and request permission
    private fun checkPermission(permission:String,  requestCode: Int) {
        if(ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED ) {

            //Requesting permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        }

    else {
        Toast.makeText(this@MainActivity,"Permission already Granted",Toast.LENGTH_SHORT).show()
        }

    }
// This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.
override fun onRequestPermissionsResult(requestCode: Int,
                                        permissions: Array<String>,
                                        grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == CAMERA_PERMISSION_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
        }
    } else if (requestCode == STORAGE_PERMISSION_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@MainActivity, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}


}