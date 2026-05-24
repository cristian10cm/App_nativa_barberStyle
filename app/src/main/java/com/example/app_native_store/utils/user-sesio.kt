package com.example.app_native_store.network

import Usuario

object UserSesion {
    var usuario: Usuario? = null

    fun login(user: Usuario) {
        usuario = user
    }

    val id get() = usuario?.id ?: ""
    val rol get() = usuario?.rol ?: ""
    val nombre get() = usuario?.nombre ?: ""
}