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

    var errorMessage = mutableStateOf<String?>(null) //  Se agrega esta variable para manejar errores
        private set

    init {
        fetchUsers() // Llamamos a la API solo una vez al iniciar
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val fetchedUsers = KtorClient().getUsers()
                if (fetchedUsers != null && fetchedUsers.users.isNotEmpty()) {
                    users.value = fetchedUsers.users
                    errorMessage.value = null //Limpiamos el error si la carga fue exitosa
                } else {
                    errorMessage.value = "No se encontraron usuarios."
                }
            } catch (e: Exception) {
                errorMessage.value = "Error al obtener usuarios: ${e.message}"
            }
        }
    }
}
