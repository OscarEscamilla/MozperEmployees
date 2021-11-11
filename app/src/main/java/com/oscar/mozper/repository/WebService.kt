package com.oscar.mozper.repository

import com.oscar.mozper.data.model.EmployeeResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface WebService {

    @GET("/")
    @Headers(
        "Content-Type: application/json",
        "language: en"
    )
    suspend fun getEmployees(): EmployeeResponse

}