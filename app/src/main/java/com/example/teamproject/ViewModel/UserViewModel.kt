package com.example.teamproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teamproject.Item.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class UserViewModel (private val repository : Repository) : ViewModel(){

    private var _userList = MutableStateFlow<List<User>>(emptyList())

    val userList = _userList.asStateFlow()

    fun InsertUser(user:User){
        viewModelScope.launch {   //viewmodel에서의 coroutine scope
            repository.InsertUser(user)
        }
    }

    fun UpdateUser(user:User){
        viewModelScope.launch {   //viewmodel에서의 coroutine scope
            repository.UpdateUser(user)
        }
    }

    fun DeleteUser(user: User){
        viewModelScope.launch {   //viewmodel에서의 coroutine scope
            repository.DeleteUser(user)
        }
    }

    fun getUsers(id:String, passwd:String){
        viewModelScope.launch {
            repository.getUsers(id, passwd).collect{
                _userList.value = it
            }
        }
    }
}