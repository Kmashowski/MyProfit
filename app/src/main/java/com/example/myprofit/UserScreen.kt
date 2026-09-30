package com.example.myprofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myprofit.viewmodel.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {

    val userList by viewModel.allUsers.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Lista de Usuarios",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val sampleName = "Usuario ${userList.size + 1}"
                val sampleAge = (20..50).random()

                viewModel.addUser(
                    sampleName,
                    sampleAge
                )
            }
        ) {
            Text("Añadir Usuario Aleatorio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(userList) { user ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {

                    Text(
                        text = "ID: ${user.id}, Nombre: ${user.name}, Edad: ${user.age}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}