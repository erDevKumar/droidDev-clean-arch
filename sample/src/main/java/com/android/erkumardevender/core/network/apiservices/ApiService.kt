package com.android.erkumardevender.core.network.apiservices

import com.android.erkumardevender.core.network.utils.WrappedListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<WrappedListResponse<Any>>
}
