package com.android.erkumardevender.user.domain.usecase

import com.android.erkumardevender.core.network.utils.BaseResult
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.core.network.utils.WrappedResponse
import com.android.erkumardevender.user.domain.UserRepository
import com.android.erkumardevender.user.domain.entity.UserEntity
import com.android.erkumardevender.user.remote.dto.UserResponse
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val userRepository: UserRepository) {
	suspend fun execute(userRequest: UserRequest): Flow<BaseResult<UserEntity, WrappedListResponse<Any>>> {
		return userRepository.profile(userRequest)
	}

}
