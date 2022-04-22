package com.android.erkumardevender.network.retrofit.apiservices

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.android.erkumardevender.network.retriofit.models.BaseResponse
import com.android.erkumardevender.network.retriofit.models.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("/v1/public/characters")
	fun getCharacters(
		@NonNull @Query("phone")phone:Long,
		@Query("apikey") apiKey: String
	) : Flow<BaseResponse<UserResponse>>
}
