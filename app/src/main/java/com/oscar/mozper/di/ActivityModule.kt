package com.oscar.mozper.di

import com.oscar.mozper.repository.EmployeeRepository
import com.oscar.mozper.repository.EmployeeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Oscar Escamilla on 18/01/2022
 **/

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repositoryImpl: EmployeeRepositoryImpl): EmployeeRepository

}