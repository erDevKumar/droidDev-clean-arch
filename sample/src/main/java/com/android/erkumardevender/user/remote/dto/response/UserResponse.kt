package com.android.erkumardevender.user.remote.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(
	@SerializedName("id") var id: Int? = null,
	@SerializedName("name") var name : String? = null,
	@SerializedName("email") var email : String? = null,
	@SerializedName("token") var token: String? = null
)
