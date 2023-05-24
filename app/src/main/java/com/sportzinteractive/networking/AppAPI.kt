package com.sportzinteractive.networking

class AppAPI {
    companion object {
        var API_BASE_URL = "https://demo.sportz.io/"

        @JvmStatic
        fun getBaseUrl(): String {
            return API_BASE_URL
        }
    }
}