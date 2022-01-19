package com.oscar.mozper.data.remote

import com.oscar.mozper.data.model.EmployeeResponse
import com.oscar.mozper.repository.WebService
import javax.inject.Inject

class RemoteEmployeeDataSource @Inject constructor(private val webService: WebService) {



    suspend fun getEmployees(): EmployeeResponse{
        return webService.getEmployees()
    }

}