fun actualizarUsuario(
    nombre: String,
    telefono: String,
    genero: String,
    edad: String,
    descripcion: String,
    ciudad: String,
    contraseña: String
) {
    UserInfo.nombre = nombre
    UserInfo.telefono = telefono
    UserInfo.genero = genero
    UserInfo.edad = edad.toIntOrNull() ?: 0
    UserInfo.descripcionPerfil = descripcion
    UserInfo.ciudad = ciudad
    UserInfo.password = contraseña
}
