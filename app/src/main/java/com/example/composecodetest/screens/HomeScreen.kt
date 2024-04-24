package com.example.composecodetest.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecodetest.models.Medicine
import com.example.composecodetest.viewmodels.homeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(onClick: (medicine:Medicine) -> Unit) {

    val homeViewModel:homeViewModel= hiltViewModel()

    val medicines=homeViewModel.medicines.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hi, ${homeViewModel.savedStateHandle.get<String>("email")} ${CurrentTime()}")
        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(medicines.value?.medicines.orEmpty()) {
                MyCard(it, onClick)
            }
        }
    }

}

@Composable
fun MyCard(medicine: Medicine,onClick: (medicine:Medicine) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)

            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEE))
            .shadow(4.dp),
       // colors = CardColors. Color.White, // Set card background color
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${medicine.name}")
            Text(text = "Dose: ${medicine.dose}")
            Text(text = "Strength: ${medicine.strength}")
        }
    }
}

@Composable
fun CurrentTime():String {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Update time every second
            currentTime = getCurrentTime()
        }
    }

    return currentTime
}

private fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}