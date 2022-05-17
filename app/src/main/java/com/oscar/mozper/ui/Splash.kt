package com.oscar.mozper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oscar.mozper.R
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.oscar.mozper.data.local.SessionManager

class Splash : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateSession()
    }



    fun validateSession(){

        Handler(Looper.getMainLooper()).postDelayed({
            val sessionManager = SessionManager(requireContext())
            if (sessionManager.isLogedin()){
                findNavController().navigate(R.id.action_splash_to_employesFragment)
            }else{
                findNavController().navigate(R.id.action_splash_to_loginFragment)
            }
        }, 3000)


    }


}