package id.daffarandika.restaurantmanagement

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.daffarandika.restaurantmanagement.adapter.MenuAdapter
import id.daffarandika.restaurantmanagement.databinding.ActivityGuestBinding
import id.daffarandika.restaurantmanagement.model.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GuestActivity : AppCompatActivity(), RecyclerViewEvent {
    val menus : MutableList<Menu> = mutableListOf()
    private val TAG = "GuestActivity"
    lateinit var binding: ActivityGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMenus(menus)
        binding.rvGuest.adapter = MenuAdapter(this, menus, this)
        binding.rvGuest.layoutManager = LinearLayoutManager(this)
    }
    override fun onItemClick(position: Int) {
        val menuid: String = menus[position].id
        val intent = Intent(this, GuestDetailActivity::class.java)
        intent.putExtra("menuid", menuid)
        startActivity(intent)
    }

    private fun getMenus(menus: MutableList<Menu>) {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute(object: Runnable {
            override fun run() {
                val conn = URL(CONSTS.url + "/menu").openConnection() as HttpURLConnection
                val jsonString = conn.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(jsonString)
                for (i in 0 until jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(i)
                    menus.add(Menu(
                        jsonObject.getString("menuid"),
                        jsonObject.getString("name"),
                        jsonObject.getString("price"),
                        jsonObject.getString("photo")
                    ))
                }
            }

        })

    }
}

