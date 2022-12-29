package id.daffarandika.restaurantmanagement

import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import androidx.appcompat.app.AppCompatActivity
import id.daffarandika.restaurantmanagement.databinding.ActivityGuestDetailBinding
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GuestDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityGuestDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("menuid")) {
            val menuid = intent.getStringExtra("menuid")
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            executor.execute(object: Runnable {
                override fun run() {
                    try {
                        val conn = URL(CONSTS.url + "/menu/" + menuid).openConnection() as HttpURLConnection
                        val jsonArray = JSONArray(conn.inputStream.bufferedReader().readText())
                        val jsonObject = jsonArray.getJSONObject(0)
                        val photo = jsonObject.getString("photo")
                        val inputStream = URL(CONSTS.url + "/images/" + photo).openConnection().getInputStream()
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        runOnUiThread {
                            binding.ivMenuPhotoDetail.setImageBitmap(bitmap)
                        }
                    } catch (e: Error) {

                    }
                }
            })
        }
    }
}