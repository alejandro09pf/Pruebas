package com.example.pruebat.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebat.Network.KtorClient
import com.example.taller1.data.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var users = mutableStateOf<List<User>>(emptyList()) // Estado mutable
        private set

    init {
        fetchUsers() // Llamamos a la API solo una vez al iniciar
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = KtorClient().getUsers()
            if (fetchedUsers != null) {
                users.value = fetchedUsers.users
            }
        }
    }
}
