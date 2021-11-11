package com.oscar.mozper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.oscar.mozper.core.Resource
import com.oscar.mozper.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers

class EmployeViewModel(private val repo: EmployeeRepository): ViewModel() {

    val fetchEmployes = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getEmployees()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}


class VMFactory(private val repo: EmployeeRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(EmployeeRepository::class.java).newInstance(repo)
    }

}