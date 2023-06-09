package com.kn.amproject

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import com.kn.amproject.activites.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting transitions, all classes that extends BaseFragment will have it implemented
        val transInflater = TransitionInflater.from(requireContext())
        enterTransition = transInflater.inflateTransition(R.transition.slide_right)
        exitTransition = transInflater.inflateTransition(R.transition.fade_out)
    }

    protected fun startApp() {
        //activate MainActivity
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            //flags are set to prevent coming back to registration activity after logging in
            //instead user will be redirected outside an app
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}