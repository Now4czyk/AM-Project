package com.kn.amproject.home

import androidx.lifecycle.ViewModel
import com.kn.amproject.data.Car
import com.kn.amproject.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val cars = repository.getCars()

    fun addFavCar(car: Car){
        repository.addFavCar(car)
    }
}