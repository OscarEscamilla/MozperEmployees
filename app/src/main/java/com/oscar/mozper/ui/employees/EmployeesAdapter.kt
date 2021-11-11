package com.oscar.mozper.ui.employees

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oscar.mozper.R
import com.oscar.mozper.data.model.Employee
import com.oscar.mozper.databinding.EmployeeItemBinding
import com.oscar.mozper.core.BaseViewHolder

class EmployeesAdapter(private val context: Context, private val items: List<Employee>, private val itemClickListener: OnEmployeeClickListener)
    : RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnEmployeeClickListener{

        fun onEmployeeClick(employee: Employee)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return  ViewHolder(LayoutInflater.from(context).inflate( R.layout.employee_item, parent, false))
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class ViewHolder(itemView: View): BaseViewHolder<Employee>(itemView){

        val binding = EmployeeItemBinding.bind(itemView)

        override fun bind(item: Employee, position: Int) {

            binding.txtName.text = "${item.firstName} ${item.lastName}"

            binding.txtDescription.text = item.description

            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.user_placeholder)
                .into(binding.imgAvatar)

            binding.employeeItem.setOnClickListener {  itemClickListener.onEmployeeClick(item)}


        }

    }

}