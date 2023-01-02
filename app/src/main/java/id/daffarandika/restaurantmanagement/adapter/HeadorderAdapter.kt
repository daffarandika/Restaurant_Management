package id.daffarandika.restaurantmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.R
import id.daffarandika.restaurantmanagement.RecyclerViewEvent
import id.daffarandika.restaurantmanagement.model.Headorder

class HeadorderAdapter(
    val context: Context,
    val headorders: MutableList<Headorder>,
    val event: RecyclerViewEvent
): RecyclerView.Adapter<HeadorderAdapter.HeadorderViewHolder>() {
    inner class HeadorderViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvOrderID: TextView = view.findViewById(R.id.tvOrderID)
        val tvEmployee: TextView = view.findViewById(R.id.tvEmployee)
        val tvMember: TextView = view.findViewById(R.id.tvMember)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvPayment: TextView = view.findViewById(R.id.tvPayment)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                event.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadorderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_headorder, parent, false)
        return HeadorderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeadorderViewHolder, position: Int) {
        val headorder = headorders[position]
        holder.tvOrderID.text = headorder.orderid
        holder.tvEmployee.text = headorder.employeeid
        holder.tvMember.text = headorder.memberid
        holder.tvDate.text = headorder.date
        holder.tvPayment.text = headorder.payment
    }

    override fun getItemCount(): Int = headorders.size
}