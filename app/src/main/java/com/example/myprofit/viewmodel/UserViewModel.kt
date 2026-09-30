package com.example.myprofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofit.data.User
import com.example.myprofit.data.UserDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDao: UserDao
) : ViewModel() {

    val allUsers: StateFlow<List<User>> = userDao.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            val newUser = User(
                name = name,
                age = age
            )

            userDao.insertUser(newUser)
        }
    }
}