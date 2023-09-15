package com.besirkaraoglu.bankingapp.data

import com.besirkaraoglu.bankingapp.core.Result
import com.besirkaraoglu.bankingapp.data.remote.RemoteDataSource
import com.besirkaraoglu.bankingapp.model.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): Repository {
    override suspend fun getData(): Flow<Result<List<RequestResult>>> = flow{
        emit(Result.Loading)
       try {
           val result = remoteDataSource.getData()
           if (result.isSuccessful){
               emit(Result.Success(result.body()!!))
           }else{
               emit(Result.Failure(Exception(result.errorBody().toString())))
           }
       }catch (e: Exception){
          emit(Result.Failure(e))
       }
    }
}