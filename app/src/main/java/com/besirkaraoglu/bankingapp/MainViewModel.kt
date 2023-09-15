package com.besirkaraoglu.bankingapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.besirkaraoglu.bankingapp.core.Result
import com.besirkaraoglu.bankingapp.data.Repository
import com.besirkaraoglu.bankingapp.model.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _currentItem = MutableLiveData<RequestResult>()
    val currentItem get() = _currentItem

    private val _bankData = MutableLiveData<Result<List<RequestResult>>>()
    val bankData get() = _bankData

    fun getData() = viewModelScope.launch {
        repository.getData().collect{
            _bankData.value = it
        }
    }

    fun setCurrentItem(item: RequestResult) = viewModelScope.launch{
        _currentItem.value = item
    }

    fun filterList(query: String): List<RequestResult>? {
        return if (bankData.value is Result.Success){
            val list = mutableListOf<RequestResult>()
            (bankData.value as Result.Success<List<RequestResult>>).data.map {
                if(it.city!!.lowercase().contains(query))
                    list.add(it)
            }
            list
        }else{
            null
        }
    }
}