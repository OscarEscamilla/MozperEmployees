package com.oscar.mozper.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.utils.AppConstants
import com.google.gson.GsonBuilder
import com.oscar.mozper.data.local.AppDatabase
import com.oscar.mozper.repository.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Oscar Escamilla on 18/01/2022
 **/

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "employees_db"
        ).build()

    @Singleton
    @Provides
    fun provideEmpleoyeeDao(db : AppDatabase) = db.employeeDao()


    @Singleton
    @Provides
    fun provideRetrofitInstance(): WebService =
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)

}