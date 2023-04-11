package com.kn.amproject.home

import androidx.lifecycle.ViewModel
import com.kn.amproject.data.Tool
import com.kn.amproject.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val tools = repository.getTools()

    fun addFavTool(tool: Tool){
        repository.addFavTool(tool)
    }
}