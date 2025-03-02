package com.example.taller1.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName:String,
    val lastName:String,
    val age:Int,
    val gender: String,
    val email: String,
    val phone: String,
    val image: String,
    val height: Double,
    val weight:Double,
    val id:Int,
    val company:Company
)

@Serializable
data class Company(
    val name:String
)
