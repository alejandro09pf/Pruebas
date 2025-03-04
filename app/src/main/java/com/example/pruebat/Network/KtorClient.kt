package com.example.pruebat.Network

import com.example.taller1.data.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url("https://dummyjson.com/")
        }

        install(Logging) {
            logger = Logger.ANDROID
            level=LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUser(id: Int): User {
        return client.get("users/$id").body()
    }

    suspend fun getUsers(): UserList {
        return client.get("users").body() //  CORREGIDO: Eliminamos "?page=2"
    }
}
// Definimos `UserList` para manejar la respuesta correctamente
@Serializable
data class UserList(
    val users: List<User>
)