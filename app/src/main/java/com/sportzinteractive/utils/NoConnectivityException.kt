package com.sportzinteractive.utils

import java.io.IOException

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "Please Check Internet Connection"
    // You can send any message whatever you want from here.
}