package com.android.erkumardevender.network.retriofit.models

data class BaseResponse<T>(
	var code: Int,
	var status: String,
	var data: T
)
