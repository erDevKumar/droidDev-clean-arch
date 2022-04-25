package com.android.erkumardevender.user.remote

import com.android.erkumardevender.core.network.apiservices.ApiService
import com.android.erkumardevender.core.network.utils.BaseResult
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.user.domain.UserRepository
import com.android.erkumardevender.user.domain.entity.UserEntity
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApi: ApiService) : UserRepository {
    override suspend fun profile(userRequest: UserRequest): Flow<BaseResult<UserEntity, WrappedListResponse<Any>>> {
        return flow {
            val response = userApi.getUsers()
            if (response.isSuccessful) {
                val body = response.body()!!
                // val loginEntity = UserEntity(body.data?.id!!, body.data?.name!!, body.data?.email!!, body.data?.token!!)
                // emit(BaseResult.Success(loginEntity))
            } else {
                val type = object : TypeToken<WrappedListResponse<Any>>() {}.type
                val err: WrappedListResponse<Any> = Gson().fromJson(response.errorBody()!!.charStream(), type)
                err.code = response.code()
                emit(BaseResult.Error(err))
            }
        }
    }
}
