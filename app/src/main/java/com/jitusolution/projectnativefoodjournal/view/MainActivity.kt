package com.jitusolution.projectnativefoodjournal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jitusolution.projectnativefoodjournal.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navView,navController)
        //bottomNav.visibility= View.GONE
        //navView.visibility=View.GONE
        setupNav()
        bottomNav.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }
    private fun setupNav() {

        findViewById<BottomNavigationView>(R.id.bottomNav)
                .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.itemReport -> showBottomNav()
                R.id.itemProfile -> showBottomNav()
                R.id.itemFood -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomNav.visibility = View.VISIBLE
        navView.visibility=View.VISIBLE

    }

    private fun hideBottomNav() {
        bottomNav.visibility = View.GONE
        navView.visibility=View.GONE

    }

    override fun onBackPressed() {
        //moveTaskToBack(true)
        finish()
        //super.onBackPressed()
    }



}