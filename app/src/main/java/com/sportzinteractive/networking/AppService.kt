package com.sportzinteractive.networking

import com.sportzinteractive.model.MatchDetailModelV2
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

@JvmSuppressWildcards
interface AppService {

    @GET
    suspend fun getMatchDetail(
        @Url url: String,
        @Header("Content-Type") contentType: String? ="application/json"
    ): MatchDetailModelV2

}