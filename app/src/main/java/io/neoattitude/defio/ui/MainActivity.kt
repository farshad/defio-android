package io.neoattitude.defio.ui

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.neoattitude.defio.R
import io.neoattitude.defio.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun businessLogic() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavMenu)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }
    }
}