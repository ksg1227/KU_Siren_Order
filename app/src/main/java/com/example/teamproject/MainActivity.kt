package com.example.teamproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.teamproject.ViewModel.Repository
import com.example.teamproject.ViewModel.UserViewModel
import com.example.teamproject.ViewModel.UserViewModelFactory
import com.example.teamproject.navigation.NavGraph
import com.example.teamproject.ui.theme.TeamProjectTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamProjectTheme {
                val table = Firebase.database.getReference("UserDB/users")

                val viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(Repository(table)))
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
//                    NavGraph(navController,viewModel)
                    NavGraph(navController,supportFragmentManager,viewModel)
                }
            }
        }
    }
}
