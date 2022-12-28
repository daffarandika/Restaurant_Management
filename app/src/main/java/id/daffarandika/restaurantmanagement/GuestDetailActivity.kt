package id.daffarandika.restaurantmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.daffarandika.restaurantmanagement.databinding.ActivityGuestDetailBinding

class GuestDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityGuestDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("menuid")) {
            binding.txt.text = intent.getStringExtra("menuid")
        } else {
            binding.txt.text = "gak ketemu"
        }
    }
}