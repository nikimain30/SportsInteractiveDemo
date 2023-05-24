package com.sportzinteractive.networking

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.sportzinteractive.AppApplicationClass.Companion.context
import com.sportzinteractive.BuildConfig
import com.sportzinteractive.networking.AppAPI.Companion.getBaseUrl
import com.sportzinteractive.utils.NetworkConnectionInterceptor
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {
    private var retrofit: Retrofit? = null

    val client: Retrofit?
        @RequiresApi(Build.VERSION_CODES.N)
        get() {
            if (retrofit == null) {
                val okHttpClient = okHttpClient
                //val okHttpClient = basic_okHttpClient
                retrofit = Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }

    private val okHttpClient: OkHttpClient
        private get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return OkHttpClient.Builder().addInterceptor(NetworkConnectionInterceptor(context))
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request()
                    val response = chain.proceed(request)
                    Log.e("RestClient", "Response Code: " + response.code())
                    response
                }
                .readTimeout(1, TimeUnit.MINUTES)
                .certificatePinner(
                    CertificatePinner.Builder()
                        .build()
                )
                .addInterceptor(
                    if (BuildConfig.DEBUG) httpLoggingInterceptor.setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    ) else httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
                )
                .connectTimeout(1, TimeUnit.MINUTES)
                .build()
        }
}