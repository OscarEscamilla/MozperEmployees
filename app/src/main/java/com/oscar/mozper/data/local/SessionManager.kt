package com.oscar.mozper.data.local

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context) {

    val MY_PREFERENCES = "Mozper"
    val USER_SESSION_KEY = "user"

    val sharedPreferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor? = sharedPreferences.edit()


    fun saveSession(data: String){
        editor?.putString(USER_SESSION_KEY, data)
        editor?.commit()
    }

    fun deleteSession(){
        editor?.clear()
        editor?.commit()
    }


    fun isLogedin(): Boolean{
        return sharedPreferences.contains(USER_SESSION_KEY)
    }


    fun getSession(): String? {
        return  if (sharedPreferences.contains(USER_SESSION_KEY)){
            sharedPreferences.getString(USER_SESSION_KEY,"")
        }else{
            ""
        }
    }



}