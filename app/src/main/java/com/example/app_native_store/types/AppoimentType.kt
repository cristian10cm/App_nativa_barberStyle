package com.example.app_native_store.types

data class AppointmentType(
    val id: String,
    val storeName: String,
    val service: String,
    val date: String,
    val time: String,
    val status: String,
    val address: String,
    val comment: String,
    val score: Number? = null,
    val userId: String
)