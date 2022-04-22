package com.android.erkumardevender.utils.extensions

import com.android.erkumardevender.network.retriofit.models.UserResponse

class UserModelExtension {

	fun UserResponse.displayInfo():String{
		return this.name+":"+this.id+":"+name+":"+phone;
	}
}
