package com.example.projetonovoecopla

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.projetonovoecopla.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Recomendo configurar startDestination no nav_graph.xml, aí não precisa dessa navegação manual:
        // if (savedInstanceState == null) {
        //     navController.navigate(R.id.telaLoginFragment)
        // }

        val navView: BottomNavigationView = binding.navView

        // Usar NavigationUI para sincronizar o BottomNavigationView com NavController
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filtroBottomSheetFragment,
                R.id.telaLoginFragment -> { // Pode esconder navView no login se quiser
                    navView.visibility = View.GONE
                }

                else -> {
                    navView.visibility = View.VISIBLE
                }
            }
        }
    }
}



