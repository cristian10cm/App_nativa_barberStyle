package com.example.app_native_store.data

import androidx.compose.runtime.mutableStateListOf
import com.example.app_native_store.types.AppointmentType

val appointmentsList = mutableStateListOf<AppointmentType>(

    AppointmentType(
        id ="1",
        storeName = "Glow Studio Chapinero",
        service = "uñas",
        date = "2026-05-05",
        time = "10:30",
        status = "Pendiente",
        address = "Cra 13 #63-45, Chapinero, Bogotá",
        comment = "Quiero diseño francés con decoración sencilla",
        score = 5,
        userId = "1"
    ),

    AppointmentType(
        id = "2",
        storeName = "Barber Kings Zona T",
        service = "barberia",
        date = "2026-05-06",
        time = "15:00",
        status = "Confirmada",
        address = "Calle 82 #12-10, Zona T, Bogotá",
        comment = "Corte fade + arreglo de barba",
        userId = "2"
    ),

    AppointmentType(
        id ="3",
        storeName = "Magic Brows Studio",
        service = "cejas",
        date = "2026-05-07",
        time = "09:00",
        status = "Completada",
        address = "Cra 19 #120-15, Usaquén, Bogotá",
        comment = "Diseño natural, no muy delgadas",
        userId = "3"
    ),

    AppointmentType(
        id ="4",
        storeName = "BarberShop",
        service = "Barberia",
        date = "2026-05-07",
        time = "09:00",
        score=4,
        status = "Completada",
        address = "Cra 19 #120-15, Bosa, Bogotá",
        comment = "Quiero un buen Diseño",
        userId = "4"
    ),
    AppointmentType(
        id ="1",
        storeName = "BarberShop",
        service = "Corte clásico",
        date = "2026-05-05",
        time = "10:00",
        score = null,
        status = "Rechazada",
        address = "Cra 10 #50-20, Bogotá",
        comment = "Corte sencillo",
        userId = "1"
    ),

    AppointmentType(
        id ="2",
        storeName = "BarberShop",
        service = "Fade",
        date = "2026-05-06",
        time = "11:30",
        score = null,
        status = "Cancelada",
        address = "Cra 30 #80-10, Bogotá",
        comment = "Quiero un fade bajo",
        userId = "2"
    ),

    AppointmentType(
        id ="3",
        storeName = "BarberShop",
        service = "Barba",
        date = "2026-05-07",
        time = "14:00",
        score = null,
        status = "Rechazada",
        address = "Cra 15 #100-25, Bogotá",
        comment = "Solo perfilado",
        userId = "3"
    ),

    AppointmentType(
        id ="4",
        storeName = "BarberShop",
        service = "Corte + Barba",
        date = "2026-05-08",
        time = "16:00",
        score = null,
        status = "Cancelada",
        address = "Cra 7 #45-60, Bogotá",
        comment = "Cambio de look",
        userId = "4"
    ),

    AppointmentType(
        id ="5",
        storeName = "BarberShop",
        service = "Diseño",
        date = "2026-05-09",
        time = "18:00",
        score = null,
        status = "Rechazada",
        address = "Cra 68 #120-40, Bogotá",
        comment = "Quiero líneas marcadas",
        userId = "5"
    )


)