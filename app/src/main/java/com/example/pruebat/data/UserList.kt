package com.example.taller1.data

import kotlinx.serialization.Serializable

@Serializable
data class UserList(

    val users:List<User>
)
@Serializable
data class Info(
    val total:Int,
    val limit:Int
)