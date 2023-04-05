package com.kn.amproject.registration

import androidx.lifecycle.ViewModel
import com.kn.amproject.data.User
import com.kn.amproject.repository.FirebaseRepository

class RegistrationViewModel: ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User){
        repository.createNewUser(user)
    }

}