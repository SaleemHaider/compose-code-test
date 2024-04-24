package com.example.composecodetest.api

import com.example.composecodetest.models.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIServices {

    @GET("/v3/fad16f3c-c8b6-4e82-a084-1601438b1353")
    suspend fun getMedicines():Response<MedicineResponse>

}