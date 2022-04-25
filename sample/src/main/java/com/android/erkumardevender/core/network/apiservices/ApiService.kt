package com.android.erkumardevender.core.network.apiservices

import androidx.annotation.NonNull
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.core.network.utils.WrappedResponse
import com.android.erkumardevender.user.remote.dto.UserResponse
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("users")
	suspend fun getUsers(
	) : Response<WrappedListResponse<Any>>
}
