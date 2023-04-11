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

        val navView: BottomNavigationView = findViewById(R.id.bottomNavView)

        val navController = findNavController(R.id.mainNavHost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.profileFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
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