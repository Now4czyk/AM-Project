package com.revolshen.firebaseseriesmini.registration

import androidx.lifecycle.ViewModel
import com.revolshen.firebaseseriesmini.data.User
import com.revolshen.firebaseseriesmini.repository.FirebaseRepository

class RegistrationViewModel: ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User){
        repository.createNewUser(user)
    }

}