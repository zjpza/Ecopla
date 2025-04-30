package com.example.projetonovoecopla

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projetonovoecopla.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        // provavelmente pode apagar agr

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_encomenda, R.id.navigation_venda, R.id.navigation_user
            )
        )

        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    return@setOnItemSelectedListener  true
                }
                R.id.navigation_encomenda -> {
                    navController.navigate(R.id.navigation_encomenda)
                    return@setOnItemSelectedListener  true
                }
                R.id.navigation_user -> {
                    navController.navigate(R.id.navigation_user)
                    return@setOnItemSelectedListener  true
                }
                R.id.navigation_venda -> {
                    navController.navigate(R.id.navigation_venda)
                    return@setOnItemSelectedListener  true
                }
                else -> false
            }
    }
}
}