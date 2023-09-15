package com.besirkaraoglu.bankingapp.data.remote

import com.besirkaraoglu.bankingapp.core.Result
import com.besirkaraoglu.bankingapp.model.RequestResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getData(): Response<List<RequestResult>>
}