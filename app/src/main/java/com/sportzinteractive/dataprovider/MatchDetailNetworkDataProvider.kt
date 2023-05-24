package com.sportzinteractive.dataprovider

import androidx.lifecycle.LiveData
import com.sportzinteractive.model.MatchDetailModel
import com.sportzinteractive.networking.Resource
import okhttp3.ResponseBody

interface MatchDetailNetworkDataProvider {
    fun getMatchDetail(url : String): LiveData<Resource<ResponseBody?>>
}