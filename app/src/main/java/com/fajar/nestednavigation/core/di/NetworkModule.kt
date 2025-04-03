package com.fajar.nestednavigation.core.di

import android.content.Context
import com.fajar.nestednavigation.core.data.DataStoreManager
import com.fajar.nestednavigation.core.data.network.AuthInterceptor
import com.fajar.nestednavigation.core.data.network.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(@ApplicationContext context: Context, dataStoreManager: DataStoreManager): AuthInterceptor {
        return AuthInterceptor(dataStoreManager, context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideProductApiService(client: OkHttpClient): ProductApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://103.179.57.26:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ProductApiService::class.java)
    }
}