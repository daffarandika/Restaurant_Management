package id.daffarandika.restaurantmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.R
import id.daffarandika.restaurantmanagement.RecyclerViewEvent
import id.daffarandika.restaurantmanagement.model.BottomSheetItem

class BottomDialogAdapter(
    val context: Context,
    val bsdItems:MutableList<BottomSheetItem>,
    val event: RecyclerViewEvent
    ): RecyclerView.Adapter<BottomDialogAdapter.BSDViewHolder>() {
    inner class BSDViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val ivBSDItem = view.findViewById<ImageView>(R.id.ivBSDItem)
        val tvBSDItem = view.findViewById<TextView>(R.id.tvBSDItem)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                event.onItemClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BSDViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bsd_row_item, parent, false)
        return BSDViewHolder(view)
    }

    override fun onBindViewHolder(holder: BSDViewHolder, position: Int) {
        holder.ivBSDItem.setImageResource(bsdItems[position].image)
        holder.tvBSDItem.setText(bsdItems[position].title)
    }

    override fun getItemCount(): Int = bsdItems.size
}