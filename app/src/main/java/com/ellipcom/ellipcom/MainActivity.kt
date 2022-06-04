package com.ellipcom.ellipcom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ellipcom.ellipcom.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView


    var cartCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomNavigation()
                R.id.registrationFragment -> hideBottomNavigation()
                //R.id.splashFragment -> hideBottomNavigation()
                R.id.welcomeFragment -> hideBottomNavigation()
                else -> showBottomNavigation()

            }
        }

        navView.setupWithNavController(navController)

        cartBadge()

    }

    private fun cartBadge() {
        val badge = navView.getOrCreateBadge(R.id.navigation_cart)
        badge.isVisible = true
        badge.number = cartCount
//        val badgeDrawable = navView.getBadge(R.id.navigation_cart)
//        if (badgeDrawable != null) {
//            badgeDrawable.isVisible = false
//            badgeDrawable.clearNumber()
//        }
    }

    private fun hideBottomNavigation() {
        navView.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        navView.visibility = View.VISIBLE
    }
}