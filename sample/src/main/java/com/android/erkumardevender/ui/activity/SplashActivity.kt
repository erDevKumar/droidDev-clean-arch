package com.android.erkumardevender.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.android.erkumardevender.R
import com.android.erkumardevender.core.data.SharedPrefs
import com.android.erkumardevender.core.network.utils.WrappedListResponse
import com.android.erkumardevender.user.domain.entity.UserEntity
import com.android.erkumardevender.user.presentation.UserInfoActivityState
import com.android.erkumardevender.user.presentation.UserViewModel
import com.android.erkumardevender.user.remote.dto.request.UserRequest
import com.android.erkumardevender.utils.showGenericAlertDialog
import com.android.erkumardevender.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        userInfoFetch()
        observe()
    }

    private fun userInfoFetch() {

        val loginRequest = UserRequest("email", "password")
        viewModel.login(loginRequest)
    }

    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: UserInfoActivityState) {
        when (state) {
            is UserInfoActivityState.Init -> Unit
            is UserInfoActivityState.ErrorUserInfo -> handleErrorLogin(state.rawResponse)
            is UserInfoActivityState.SuccessUserInfo -> handleSuccessLogin(state.userEntity)
            is UserInfoActivityState.ShowToast -> showToast(state.message)
            is UserInfoActivityState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleErrorLogin(response: WrappedListResponse<Any>) {
        showGenericAlertDialog(response.message)
    }

    private fun handleLoading(isLoading: Boolean) {
    }

    private fun handleSuccessLogin(userEntity: UserEntity) {
        sharedPrefs.saveToken(userEntity.token)
    }
}
