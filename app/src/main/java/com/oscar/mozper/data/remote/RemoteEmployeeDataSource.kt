package com.oscar.mozper.data.remote

import com.oscar.mozper.data.model.EmployeeResponse
import com.oscar.mozper.repository.WebService

class RemoteEmployeeDataSource(private val webService: WebService) {

    suspend fun getEmployees(): EmployeeResponse{
        return webService.getEmployees()
    }

}