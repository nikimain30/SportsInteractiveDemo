package com.sportzinteractive.utils

import android.widget.Toast
import com.sportzinteractive.AppApplicationClass

fun showToastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(AppApplicationClass.context, message, duration).show()
}