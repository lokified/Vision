package com.loki.britam.di

import com.loki.britam.core.Constants.BASE_URL
import com.loki.britam.data.remote.VisionApi
import com.loki.britam.data.remote.repository.auth.AuthRepository
import com.loki.britam.data.remote.repository.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): VisionApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VisionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(api: VisionApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}