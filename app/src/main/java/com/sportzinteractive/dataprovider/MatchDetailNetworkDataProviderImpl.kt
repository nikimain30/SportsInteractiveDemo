package com.sportzinteractive.dataprovider

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.liveData
import com.sportzinteractive.networking.AppService
import com.sportzinteractive.networking.Resource
import com.sportzinteractive.networking.RestClient
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class MatchDetailNetworkDataProviderImpl : MatchDetailNetworkDataProvider {
    @RequiresApi(Build.VERSION_CODES.N)
    private val appServices = RestClient.client?.create(AppService::class.java)

    override  fun getMatchDetail(url : String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                val response = appServices?.getMatchDetail(url)
                emit(Resource.success(data = response))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.error(data = null, message = (e as? HttpException)?.response()?.errorBody()?.string() ?: "Error occured"))

            }
        }
}