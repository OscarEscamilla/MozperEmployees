package com.oscar.mozper.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oscar.mozper.data.model.EmployeeEntity

@Dao
interface EmployeeDao {


    @Query("SELECT * FROM employeeentity")
    suspend fun getAllEmployees(): List<EmployeeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEmployee(employee: EmployeeEntity)


}