package com.android.erkumardevender.utils.extensions

import com.android.erkumardevender.user.remote.dto.UserResponse


class UserModelExtension {

	fun UserResponse.displayInfo():String{
		return this.name+":"+this.id+":"+name;
	}
}
