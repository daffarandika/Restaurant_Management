package id.daffarandika.restaurantmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.R
import id.daffarandika.restaurantmanagement.RecyclerViewEvent
import id.daffarandika.restaurantmanagement.model.Member

class MemberAdapter(
    val context: Context,
    val members: MutableList<Member>,
    val event: RecyclerViewEvent
): RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    inner class MemberViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvName = view.findViewById<TextView>(R.id.tvMemberName)
        val tvEmail = view.findViewById<TextView>(R.id.tvMemberEmail)
        val tvHandphone = view.findViewById<TextView>(R.id.tvMemberHandphone)
        val tvJoindate = view.findViewById<TextView>(R.id.tvMemberJoinDate)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.tvName.text = member.name
        holder.tvEmail.text = member.email
        holder.tvHandphone.text = member.handphone
        holder.tvJoindate.text = member.joinDate
    }

    override fun getItemCount(): Int = members.size
}