package com.example.myprofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myprofit.data.AppDatabase
import com.example.myprofit.ui.theme.MyProfitTheme
import com.example.myprofit.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getInstance(this)
        val userDao = db.userDao()

        val viewModelFactory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {

                @Suppress("UNCHECKED_CAST")
                return UserViewModel(userDao) as T
            }
        }

        val viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[UserViewModel::class.java]

        setContent {
            MyProfitTheme {
                UserScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}