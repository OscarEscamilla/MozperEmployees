package com.oscar.mozper.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oscar.mozper.data.model.EmployeeEntity

@Database(entities = arrayOf(EmployeeEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {

        private var INSTANCE: AppDatabase? = null


        fun getDataBase(context: Context): AppDatabase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "employees_db"
            ).build()

            return  INSTANCE!!
        }
    }

}