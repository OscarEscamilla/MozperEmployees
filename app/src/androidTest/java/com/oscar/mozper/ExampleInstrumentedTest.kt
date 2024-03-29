package com.oscar.mozper

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oscar.mozper.data.local.AppDatabase
import com.oscar.mozper.data.local.EmployeeDao
import com.oscar.mozper.data.model.Employee
import com.oscar.mozper.data.model.toEmployeeEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import com.google.common.truth.Truth;
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.matchers.JUnitMatchers.*

import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest: TestCase() {

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