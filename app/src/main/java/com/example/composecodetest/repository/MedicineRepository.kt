package com.example.composecodetest.repository

import com.example.composecodetest.api.APIServices
import com.example.composecodetest.models.Medicine
import com.example.composecodetest.models.MedicineResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MedicineRepository @Inject constructor(private val apiServices: APIServices) {

    private val _medicines= MutableStateFlow<MedicineResponse?>(null)
    val medicines:StateFlow<MedicineResponse?>
        get() = _medicines
    suspend fun getMedicines(){
        val response = apiServices.getMedicines()

        if(response.isSuccessful && response.body() != null)
        {
            _medicines.emit(response.body())

        }


    }

}