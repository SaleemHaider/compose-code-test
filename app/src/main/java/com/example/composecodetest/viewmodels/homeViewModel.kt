package com.example.composecodetest.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecodetest.models.MedicineResponse
import com.example.composecodetest.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class homeViewModel @Inject constructor(private val repository: MedicineRepository,
                                         val savedStateHandle: SavedStateHandle) :ViewModel(){
    val medicines:StateFlow<MedicineResponse?>
       get()= repository.medicines

    init {
        viewModelScope.launch {
            repository.getMedicines()
        }
    }
}