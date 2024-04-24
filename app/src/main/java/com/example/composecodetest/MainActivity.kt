package com.example.composecodetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecodetest.models.Medicine
import com.example.composecodetest.screens.HomeScreen
import com.example.composecodetest.screens.LoginScreen
import com.example.composecodetest.screens.MedicineDetailScreen
import com.example.composecodetest.ui.theme.ComposeCodeTestTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold (
                topBar = {
                    TopAppBar(title = { 
                        Text(text = "Compose Code Test")
                    },   colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF348fd9),
                        titleContentColor = Color.White)
                    )
                }
            ){
                Box(modifier = Modifier.padding(it)) {
                    App()
                }
            }



        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable(route = "login") {
                LoginScreen {
                    var email:String
                    if(it.equals(""))
                        email="Guest"
                    else
                        email=it
                    navController.navigate("home/${email}")
                }
            }
            composable(route = "home/{email}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                    }
                )
            ) {
                ComposeCodeTestTheme{
                    HomeScreen{
                        val gson = Gson()
                        var myObjectString = gson.toJson(it, Medicine::class.java)
                        navController.navigate("medicineDetails/$myObjectString")}
                }

            }
            composable(route = "medicineDetails/{email}") {
                ComposeCodeTestTheme {
                    MedicineDetailScreen()

                }            }

        }
    }
}


