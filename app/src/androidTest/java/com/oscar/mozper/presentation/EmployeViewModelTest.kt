package com.oscar.mozper.presentation

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.oscar.mozper.data.local.AppDatabase
import com.oscar.mozper.data.local.LocalEmployeeDataSource
import com.oscar.mozper.data.remote.RemoteEmployeeDataSource
import com.oscar.mozper.repository.EmployeeRepositoryImpl
import com.oscar.mozper.repository.RetrofitClient
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.google.common.truth.Truth
import org.junit.Rule


@RunWith(JUnit4::class)
class EmployeViewModelTest: TestCase(){

    private lateinit var viewModel: EmployeViewModel

    //@get:Rule
    //val instantTaskExecutor = InstantTaskExecutorRule()



    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()

        val  db = Room.inMemoryDatabaseBuilder(
        context, AppDatabase::class.java
        ).allowMainThreadQueries().build()


        val dataSource = LocalEmployeeDataSource(db.employeeDao())
        val remote = RemoteEmployeeDataSource(RetrofitClient.webservice)


        val repositoryImpl = EmployeeRepositoryImpl(remote,dataSource)


        viewModel = EmployeViewModel(repositoryImpl)


    }


    @Test
    fun testEmployyeViewModel(){

        var result = viewModel.fetchEmployes


        Truth.assertThat(result != null).isTrue()
    }



}