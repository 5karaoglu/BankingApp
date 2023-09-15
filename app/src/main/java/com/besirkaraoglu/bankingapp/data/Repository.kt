package com.besirkaraoglu.bankingapp.data

import com.besirkaraoglu.bankingapp.core.Result
import com.besirkaraoglu.bankingapp.model.RequestResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getData(): Flow<Result<List<RequestResult>>>
}