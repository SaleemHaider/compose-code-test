package com.example.composecodetest.models

data class Medicine(
    val name: String,
    val dose: String,
    val strength: String
)

data class MedicineResponse(
    val medicines: List<Medicine>
)

