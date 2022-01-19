package com.oscar.mozper.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.oscar.mozper.core.Resource
import com.oscar.mozper.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EmployeViewModel @ViewModelInject constructor(private val repo: EmployeeRepository): ViewModel() {

    val fetchEmployes = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getEmployees()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}
