fun actualizarUserStore(
    nombre: String,
    telefono: String,
    ciudad: String,
    contraseña: String
) {
    UserInfo.nombre = nombre
    UserInfo.telefono = telefono
    UserInfo.ciudad = ciudad
    UserInfo.password = contraseña
}