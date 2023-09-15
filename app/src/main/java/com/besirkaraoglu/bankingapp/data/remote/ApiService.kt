package com.besirkaraoglu.bankingapp.data.remote

import com.besirkaraoglu.bankingapp.core.BASE_PATH
import com.besirkaraoglu.bankingapp.model.RequestResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(BASE_PATH)
    suspend fun getData(): Response<List<RequestResult>>

}