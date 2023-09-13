package com.besirkaraoglu.bankingapp.core

import com.besirkaraoglu.bankingapp.data.Repository
import com.besirkaraoglu.bankingapp.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository = RepositoryImpl()
}