package id.daffarandika.restaurantmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.adapter.MenuAdapter
import id.daffarandika.restaurantmanagement.databinding.ActivityGuestBinding
import id.daffarandika.restaurantmanagement.model.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class GuestActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val menus : MutableList<Menu> = mutableListOf()
        getMenus(menus)
        binding.rvGuest.adapter = MenuAdapter(this, menus)
        binding.rvGuest.layoutManager = LinearLayoutManager(this)
    }

    private fun getMenus(menus: MutableList<Menu>) = runBlocking{
        launch(Dispatchers.IO) {
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
    }
}