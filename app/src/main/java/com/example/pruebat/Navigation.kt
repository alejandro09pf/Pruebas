package com.example.pruebat

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pruebat.screens.HomeScreen
import com.example.pruebat.screens.UserDetail
import com.example.taller1.data.User
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import android.net.Uri
@Serializable
sealed interface Routes {
    @Serializable
    object Home

    @Serializable
    data class UserDetail(val userJson:String)
}
@Composable
fun NavigationStack(modifier: Modifier=Modifier){
    val navController=rememberNavController()
    NavHost(navController=navController, startDestination = Routes.Home, modifier = modifier){
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable("userDetail/{userJson}") { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("userJson") ?: ""
            val decodedJson = Uri.decode(userJson)
            val user = Json.decodeFromString<User>(userJson)

            UserDetail(user = user)
        }
    }
}