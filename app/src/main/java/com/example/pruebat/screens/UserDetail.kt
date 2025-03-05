package com.example.pruebat.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pruebat.Network.KtorClient
import com.example.pruebat.R
import com.example.pruebat.Routes
import com.example.pruebat.ui.components.Loader
import com.example.taller1.data.User
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

@Composable
fun UserDetail(user: User) {
    val context = LocalContext.current

    LazyColumn(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
        if (user == null) {
            item {
                Loader(modifier = Modifier.fillParentMaxSize())
            }
        } else {
            item {
                Card(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .padding(8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = user!!.image,
                            contentDescription = "User picture",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Text(
                                user!!.firstName,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        mapOf(
                            stringResource(R.string.age_label) to user!!.age,
                            stringResource(R.string.email_label) to user!!.email,
                            stringResource(R.string.compay_label) to user!!.company,
                            stringResource(R.string.Heigh_label) to user!!.height,
                            stringResource(R.string.Weight_label) to user!!.weight
                        ).forEach { entry ->
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    entry.key,
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                                Text(
                                    entry.value.toString(),
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        // Boton de llamada al final de la info del contacto
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${user.phone}")
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "Llamar a ${user.firstName}")
                        }
                    }
                }
            }
        }
    }
}

