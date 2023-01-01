package id.daffarandika.restaurantmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.R
import id.daffarandika.restaurantmanagement.RecyclerViewEvent
import id.daffarandika.restaurantmanagement.model.Employee

class EmployeeAdapter(
    val context: Context,
    val employees: MutableList<Employee>,
    val event: RecyclerViewEvent
): RecyclerView.Adapter<EmployeeAdapter.viewHolder>() {
    inner class viewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                event.onItemClick(position)
            }
        }

        val tvName = view.findViewById<TextView>(R.id.tvEmpName)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmpEmail)
        val tvHandphone = view.findViewById<TextView>(R.id.tvEmpHandphone)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_employee, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val employee = employees[position]
        holder.tvName.text = employee.name
        holder.tvEmail.text = employee.email
        holder.tvHandphone.text = employee.handphone
    }

    override fun getItemCount(): Int = employees.size
}