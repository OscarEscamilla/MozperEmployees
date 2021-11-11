package com.oscar.mozper.data.local

import com.oscar.mozper.data.model.EmployeeEntity
import com.oscar.mozper.data.model.EmployeeResponse
import com.oscar.mozper.data.model.toEmployeeList

class LocalEmployeeDataSource(private val employeeDao: EmployeeDao) {


    suspend fun getEmployees(): EmployeeResponse {
        return employeeDao.getAllEmployees().toEmployeeList()
    }


    suspend fun saveEmployee(employeeEntity : EmployeeEntity){
        employeeDao.saveEmployee(employeeEntity)
    }

}