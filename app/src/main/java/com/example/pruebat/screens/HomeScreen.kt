package com.example.pruebat.screens

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.compose.AppTheme
import com.example.pruebat.Network.KtorClient
import com.example.pruebat.Routes
import com.example.pruebat.ui.components.Loader
import com.example.taller1.data.User
import com.example.pruebat.viewModel.UserViewModel
import kotlinx.serialization.json.Json

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    viewModel: UserViewModel= viewModel()
){
    val context= LocalContext.current
    var users by viewModel.users

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ){
        stickyHeader {
            Row(
                modifier=Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Users: ${users.size}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                OutlinedButton(onClick = {
                    users= listOf()
                }) {
                    Text(
                        "Borrar Usuarios",
                        color=MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        if (users.isEmpty()){
            item{
                Loader(modifier=Modifier.fillParentMaxSize())
            }
        }else{
            items(users){usr->
                ListItem(
                    headlineContent = {
                        Text("${usr.firstName}-(${usr.gender})",
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                            )
                    },
                    supportingContent = {
                        Text(
                            "${usr.company.name}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    leadingContent = {
                        AsyncImage(
                            model=usr.image,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(4.dp, MaterialTheme.colorScheme.tertiary)
                        )
                    },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.TwoTone.PlayArrow,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.clickable {
                        Toast.makeText(context, "Tap on ${usr.firstName}", Toast.LENGTH_SHORT).show()
                        val userJson = Uri.encode(Json.encodeToString(usr))
                        navController.navigate("userDetail/$userJson")
                    },
                )
            HorizontalDivider()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    AppTheme {
        HomeScreen(navController = rememberNavController())
    }
}