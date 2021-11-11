package com.oscar.mozper.repository

import com.oscar.mozper.data.model.EmployeeResponse

interface EmployeeRepository {

    suspend fun getEmployees(): EmployeeResponse
}