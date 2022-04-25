package com.android.erkumardevender.user.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.erkumardevender.core.network.utils.BaseResult
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.user.domain.entity.UserEntity
import com.android.erkumardevender.user.domain.usecase.UserInfoUseCase
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userInfoUseCase: UserInfoUseCase) : ViewModel() {
    private val state = MutableStateFlow<UserInfoActivityState>(UserInfoActivityState.Init)
    val mState: StateFlow<UserInfoActivityState> get() = state

    private fun setLoading() {
        state.value = UserInfoActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = UserInfoActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = UserInfoActivityState.ShowToast(message)
    }

    fun login(loginRequest: UserRequest) {
        viewModelScope.launch {
            userInfoUseCase.execute(loginRequest)
                .onStart {
                    setLoading()
                }.catch {
                    exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }.collect {
                    baseResult: BaseResult<UserEntity, WrappedListResponse<Any>> ->
                    hideLoading()
                    when (baseResult) {
                        is BaseResult.Error -> state.value = UserInfoActivityState.ErrorUserInfo(baseResult.rawResponse)
                        is BaseResult.Success -> state.value = UserInfoActivityState.SuccessUserInfo(baseResult.data)
                    }
                }
        }
    }
}

sealed class UserInfoActivityState {
    object Init : UserInfoActivityState()
    data class IsLoading(val isLoading: Boolean) : UserInfoActivityState()
    data class ShowToast(val message: String) : UserInfoActivityState()
    data class SuccessUserInfo(val userEntity: UserEntity) : UserInfoActivityState()
    data class ErrorUserInfo(val rawResponse: WrappedListResponse<Any>) : UserInfoActivityState()
}
