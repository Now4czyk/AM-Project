package com.kn.amproject.activites

import com.kn.amproject.preferences.ContextWrapper
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kn.amproject.R
import com.kn.amproject.preferences.LanguagePreference

class MainActivity : AppCompatActivity() {
    //languages
    lateinit var languagePreference: LanguagePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //catching our view
        val navView: BottomNavigationView = findViewById(R.id.bottomNavView)

        //the place of our fragment to be changed while switching
        val navController = findNavController(R.id.mainNavHost)
        //connection with appBar which is the bar at the top of our app so that we have proper
        // titles which are home and profile
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.profileFragment)
        )

        //connecting app bar with navigation
        setupActionBarWithNavController(navController, appBarConfiguration)
        //settting at the bottom our navigation
        navView.setupWithNavController(navController)

        //languages
        languagePreference = LanguagePreference(this)
    }

    override fun attachBaseContext(newBase: Context?) {
        languagePreference = LanguagePreference(newBase!!)
        val lang = languagePreference.getLanguage()
        super.attachBaseContext(ContextWrapper.wrap(newBase, lang!!))
    }
}