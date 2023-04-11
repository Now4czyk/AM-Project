package com.kn.amproject.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kn.amproject.data.Tool
import com.kn.amproject.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    val user = repository.getUserData()
    val favTools = user.switchMap {
        repository.getFavTools(it.favTools)
    }
    fun removeFavTool(tool: Tool){
        repository.removeFavTool(tool)
    }
    fun editProfileData(map: Map<String, String>){
        repository.editProfileData(map)
    }
    fun uploadUserPhoto(bytes: ByteArray){
        repository.uploadUserPhoto(bytes)
    }

}