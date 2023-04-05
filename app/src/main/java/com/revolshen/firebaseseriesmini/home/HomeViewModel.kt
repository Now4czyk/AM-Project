package com.revolshen.firebaseseriesmini.home

import androidx.lifecycle.ViewModel
import com.revolshen.firebaseseriesmini.data.Car
import com.revolshen.firebaseseriesmini.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val cars = repository.getCars()

    fun addFavCar(car: Car){
        repository.addFavCar(car)
    }
}