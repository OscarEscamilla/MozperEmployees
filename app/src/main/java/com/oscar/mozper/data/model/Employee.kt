package com.oscar.mozper.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val description: String,
    val firstName: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val rating: Float
): Parcelable


// Room
@Entity
data class EmployeeEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "rating")
    val rating: Float
)


fun List<EmployeeEntity>.toEmployeeList(): EmployeeResponse {
    val result = mutableListOf<Employee>()

    this.forEach { employeeEntity ->
        result.add(employeeEntity.toEmployee())
    }

    return EmployeeResponse(result)
}


fun EmployeeEntity.toEmployee(): Employee = Employee(
    this.description,
    this.firstName,
    this.id,
    this.image,
    this.lastName,
    this.rating
)


fun Employee.toEmployeeEntity(): EmployeeEntity = EmployeeEntity(
    this.id,
    this.description,
    this.firstName,
    this.image,
    this.lastName,
    this.rating
)
