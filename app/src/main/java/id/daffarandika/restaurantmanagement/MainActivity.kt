package id.daffarandika.restaurantmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import id.daffarandika.restaurantmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().apply {
            setReorderingAllowed(true)
            replace(R.id.fragment, BottomDialogHomeFragment())
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

}