package id.daffarandika.restaurantmanagement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.daffarandika.restaurantmanagement.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}