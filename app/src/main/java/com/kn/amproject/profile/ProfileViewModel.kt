package com.kn.amproject.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kn.amproject.data.Car
import com.kn.amproject.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    val user = repository.getUserData()
    val favCars = user.switchMap {
        repository.getFavCars(it.favCars)
    }
    fun removeFavCar(car: Car){
        repository.removeFavCar(car)
    }
    fun editProfileData(map: Map<String, String>){
        repository.editProfileData(map)
    }
    fun uploadUserPhoto(bytes: ByteArray){
        repository.uploadUserPhoto(bytes)
    }

}