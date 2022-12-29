package id.daffarandika.restaurantmanagement.adapter

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.CONSTS
import id.daffarandika.restaurantmanagement.R
import id.daffarandika.restaurantmanagement.RecyclerViewEvent
import id.daffarandika.restaurantmanagement.model.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MenuAdapter(private val context: Context,
                  private val menus : MutableList<Menu>,
                  private val rve: RecyclerViewEvent
                  ) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    inner class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)
        val ivMenuPhoto = view.findViewById<ImageView>(R.id.ivMenuPhoto)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                rve.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menus[position]
        updateUI(holder, menu)
    }

    private fun updateUI(holder: MenuViewHolder, menu: Menu) {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        val url = menu.photo
        val inputStream =
            URL(CONSTS.url + "/images/" + url).openConnection().getInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStream)

        executor.execute(object: Runnable {
            override fun run() {
            }
        })

        (context as Activity).runOnUiThread(object: Runnable {
            override fun run() {
                val resources = context.resources
                holder.tvMenuName.text = menu.name
                holder.tvMenuPrice.text = resources.getString(R.string.price, menu.price)
                holder.ivMenuPhoto.setImageBitmap(bitmap)
            }
        })

    }

    override fun getItemCount(): Int = menus.size
}