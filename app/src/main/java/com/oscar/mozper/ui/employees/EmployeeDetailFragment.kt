package com.oscar.mozper.ui.employees


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.oscar.mozper.databinding.FragmentEmployeDetailBinding
import androidx.activity.OnBackPressedCallback
import com.oscar.mozper.R
import com.oscar.mozper.utils.toast


class EmployeeDetailFragment : Fragment() {

    private var _binding: FragmentEmployeDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EmployeeDetailFragmentArgs>()

    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = binding.toolbarEmployeesDetail

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        Glide.with(requireContext())
            .load(args.currentEmployee.image)
            .centerCrop()
            .placeholder(R.drawable.user_placeholder)
            .into(binding.userAvatar)

        binding.txtFullName.text = "${args.currentEmployee.firstName} ${args.currentEmployee.lastName}"

        binding.txtDescription.text = args.currentEmployee.description

        binding.ratingEmployee.rating = args.currentEmployee.rating
    }







}