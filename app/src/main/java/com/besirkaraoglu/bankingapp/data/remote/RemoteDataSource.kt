package com.besirkaraoglu.bankingapp.data.remote

import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getData(): Flow<Result<Any>>
}