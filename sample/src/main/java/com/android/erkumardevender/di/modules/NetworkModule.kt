package com.android.erkumardevender.di.modules

import com.android.erkumardevender.BuildConfig
import com.android.erkumardevender.network.retrofit.apiservices.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Provides
	fun provideApiService(): ApiService {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.REMOTE_API_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(ApiService::class.java)
	}
}
