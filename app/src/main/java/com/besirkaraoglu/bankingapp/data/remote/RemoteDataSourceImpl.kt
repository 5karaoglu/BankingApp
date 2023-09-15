package com.besirkaraoglu.bankingapp.data.remote

import com.besirkaraoglu.bankingapp.model.RequestResult
import retrofit2.Response

class RemoteDataSourceImpl(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getData(): Response<List<RequestResult>> = apiService.getData()
}