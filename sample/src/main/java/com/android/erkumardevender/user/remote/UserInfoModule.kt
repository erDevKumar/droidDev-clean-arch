package com.android.erkumardevender.user.remote

import com.android.erkumardevender.core.network.NetworkModule
import com.android.erkumardevender.core.network.apiservices.ApiService
import com.android.erkumardevender.user.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class UserInfoModule {

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserInfoRepository(loginApi: ApiService): UserRepository {
        return UserRepositoryImpl(loginApi)
    }
}
