package com.sportzinteractive.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.sportzinteractive.baseclasses.BaseViewModel
import com.sportzinteractive.dataprovider.MatchDetailNetworkDataProviderImpl
import com.sportzinteractive.model.MatchDetailModelV2
import com.sportzinteractive.networking.Resource
import okhttp3.ResponseBody

class MatchDetailViewModel(application: Application) : BaseViewModel(application)  {

    private val networkDataProviderImpl = MatchDetailNetworkDataProviderImpl()

    fun getMatchDetail(url :String): LiveData<Resource<MatchDetailModelV2?>> {
        return  networkDataProviderImpl.getMatchDetail(url)

    }
}