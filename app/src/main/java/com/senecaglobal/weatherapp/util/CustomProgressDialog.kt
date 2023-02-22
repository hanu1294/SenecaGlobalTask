package com.senecaglobal.weatherapp.util

import android.app.Activity
import android.app.AlertDialog
import com.senecaglobal.weatherapp.R

class CustomProgressDialog(val activity: Activity) {

    private lateinit var loadingDialog: AlertDialog

    fun showProgressDialog() {
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_loader, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)

        loadingDialog = builder.create()
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        loadingDialog.show()
    }

    fun cancelDialog() {
        loadingDialog.dismiss()
    }
}