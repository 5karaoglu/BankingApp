package com.besirkaraoglu.bankingapp.core

sealed class Result<out R>{

    object Loading: Result<Nothing>()

    class Failure(e: Exception): Result<Nothing>()

    class Success<R>(val data: R): Result<R>()
}
