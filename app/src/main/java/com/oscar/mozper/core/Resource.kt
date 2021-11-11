package com.oscar.mozper.core

sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Succes<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val exception: Exception ): Resource<T>()
}