package com.example.projetonovoecopla

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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

        // Navegação personalizada
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_encomenda -> {
                    navController.navigate(R.id.navigation_encomenda)
                    true
                }
                R.id.navigation_user -> {
                    navController.navigate(R.id.navigation_user)
                    true
                }
                R.id.navigation_venda -> {
                    navController.navigate(R.id.navigation_venda)
                    true
                }
                else -> false
            }
        }

        // Listener para esconder ou mostrar a BottomNavigationView
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filtroBottomSheetFragment,
                R.id.navigation_home,
                R.id.navigation_encomenda,
                R.id.navigation_user,
                R.id.navigation_venda -> {
                    navView.visibility = View.VISIBLE
                }
                else -> {
                    navView.visibility = View.GONE
                }
            }
        }
    }
}
