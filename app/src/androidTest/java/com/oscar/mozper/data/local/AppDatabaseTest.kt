package com.oscar.mozper.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.oscar.mozper.data.model.Employee
import com.oscar.mozper.data.model.toEmployeeEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AppDatabaseTest(): TestCase(){
    private lateinit var employeeDao: EmployeeDao
    private lateinit var db: AppDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        employeeDao = db.employeeDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun writeAndReadEmployees() = runBlocking {
        val employee = Employee("Desription", "Oscar", 1, "image", "Escamilla",3f)
        employeeDao.saveEmployee(employee.toEmployeeEntity())
        val employees = employeeDao.getAllEmployees()
        //assertThat(employees.contains(employee)).isTrue()
        Truth.assertThat(employees.contains(employee.toEmployeeEntity())).isTrue()
    }
}