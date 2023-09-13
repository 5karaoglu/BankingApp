package com.besirkaraoglu.bankingapp.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl: RemoteDataSource {
    override fun getData(): Flow<Result<Any>> = flow{

    }
}