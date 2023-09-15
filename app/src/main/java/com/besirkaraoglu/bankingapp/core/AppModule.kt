package com.besirkaraoglu.bankingapp.core

import com.besirkaraoglu.bankingapp.data.Repository
import com.besirkaraoglu.bankingapp.data.RepositoryImpl
import com.besirkaraoglu.bankingapp.data.remote.ApiService
import com.besirkaraoglu.bankingapp.data.remote.RemoteDataSource
import com.besirkaraoglu.bankingapp.data.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource = RemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository = RepositoryImpl(remoteDataSource)
}