package com.oscar.mozper.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oscar.mozper.R
import com.oscar.mozper.data.local.SessionManager
import com.oscar.mozper.databinding.FragmentLoginBinding
import android.text.TextUtils
import android.util.Patterns
import androidx.activity.OnBackPressedCallback
import java.util.regex.Pattern


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }


    fun setupListeners(){

        binding.btnLogin.setOnClickListener {
            if(binding.txtEmail.text.isNullOrEmpty()){

                binding.emailLayuot.error = "Enter an email"

            }else if (binding.txtPassword.text.isNullOrEmpty()){

                binding.passwordLayout.error = "Enter a password"

            }else{

                if (!binding.txtEmail.text.isValidEmail()){
                    binding.emailLayuot.error = "Email is invalid"
                }else{
                    var sessionManager = SessionManager(requireContext())
                    sessionManager.saveSession(binding.txtEmail.text.toString())

                    findNavController().navigate(R.id.action_loginFragment_to_employesFragment)
                }
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


    private fun CharSequence.isValidPassword(): Boolean {

        val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\!\\@\\#\\$]{6,8}"
        )
        return !TextUtils.isEmpty(this) && PASSWORD_PATTERN.matcher(this).matches()
    }


}