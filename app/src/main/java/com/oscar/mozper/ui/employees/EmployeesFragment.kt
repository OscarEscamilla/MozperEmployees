package com.oscar.mozper.ui.employees

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oscar.mozper.R
import com.oscar.mozper.core.Resource

import com.oscar.mozper.data.model.Employee
import com.oscar.mozper.databinding.FragmentEmployesBinding
import com.oscar.mozper.presentation.EmployeViewModel

import com.oscar.mozper.utils.toast
import androidx.activity.OnBackPressedCallback
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.oscar.mozper.core.SessionManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeesFragment : Fragment(), EmployeesAdapter.OnEmployeeClickListener {


    private var _binding: FragmentEmployesBinding? = null
    private val binding get() = _binding!!

    var toolbar: Toolbar? = null


    private val viewModel by viewModels<EmployeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_toolbar_employees, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.itemLogout -> {
                showAlertLogout()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun showAlertLogout() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Log out") { dialog, id ->
                    val sessionManager = SessionManager(requireContext())
                    context?.toast("coming out... ${sessionManager.getSession()}")
                    sessionManager.deleteSession()
                    findNavController().navigate(R.id.action_employesFragment_to_loginFragment)
                }
                setNegativeButton("Cancel") { dialog, id ->
                    dialog.dismiss()


                }
            }

            builder?.setMessage("Are you sure to log out?")
                .setTitle("Log out")

            builder.create()
        }

        alertDialog?.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployesBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewComponents()

        viewModel.fetchEmployes.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {

                    Log.d("LiveData2", "Loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {

                    Log.d("LiveData2", "${result.data.employees}")
                    binding.progressBar.visibility = View.GONE
                    binding.rvEmployes.adapter =
                        EmployeesAdapter(requireContext(), result.data.employees, this)

                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    context?.toast("Ha ocurrido un error al obtener los datos")
                    Log.d("LiveData2", "${result.exception}")
                }
            }
        }
    }


    override fun onEmployeeClick(employee: Employee) {

        val action =
            EmployeesFragmentDirections.actionEmployesFragmentToEmployeDetailFragment(employee)
        findNavController().navigate(action)

    }


    private fun initViewComponents() {
        binding.rvEmployes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvEmployes.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        toolbar = binding.toolbarEmployees

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
