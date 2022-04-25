package com.android.erkumardevender.user.domain

import com.android.erkumardevender.core.network.utils.BaseResult
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.user.domain.entity.UserEntity
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun profile(userRequest: UserRequest): Flow<BaseResult<UserEntity, WrappedListResponse<Any>>>
}
