package com.oscar.mozper.repository

import android.util.Log
import com.oscar.mozper.core.InternetCheck
import com.oscar.mozper.data.local.LocalEmployeeDataSource
import com.oscar.mozper.data.model.EmployeeResponse
import com.oscar.mozper.data.model.toEmployeeEntity
import com.oscar.mozper.data.remote.RemoteEmployeeDataSource

class EmployeeRepositoryImpl(
    private val dataSourceRemote: RemoteEmployeeDataSource,
    private val dataSourceLocal: LocalEmployeeDataSource
) : EmployeeRepository {


    override suspend fun getEmployees(): EmployeeResponse {

        // single source of truth
        return if (InternetCheck.isNetworkAvailable()) {

            dataSourceRemote.getEmployees().employees.forEach { employee ->
                dataSourceLocal.saveEmployee(employee.toEmployeeEntity())
            }

            dataSourceLocal.getEmployees()

        } else {

            dataSourceLocal.getEmployees()
        }


    }


}