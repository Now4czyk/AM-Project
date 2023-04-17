package com.kn.amproject.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.kn.amproject.data.Tool
import com.kn.amproject.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    val user = repository.getUserData()
    val favTools = user.switchMap {
        //switchMap that allows to fetch only these tools that are favourite
        repository.getFavTools(it.favTools)
    }

    fun removeFavTool(tool: Tool, context: Context, msg: String) {
        repository.removeFavTool(tool, context, msg)
    }

    fun editProfileData(map: Map<String, String>) {
        repository.editProfileData(map)
    }

    fun uploadUserPhoto(bytes: ByteArray) {
        repository.uploadUserPhoto(bytes)
    }

}