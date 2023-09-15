package com.besirkaraoglu.bankingapp.core

sealed class Result<out T>{

    object Loading: Result<Nothing>()

    class Failure(val e: Exception): Result<Nothing>()

    class Success<R>(val data: R): Result<R>()
}
