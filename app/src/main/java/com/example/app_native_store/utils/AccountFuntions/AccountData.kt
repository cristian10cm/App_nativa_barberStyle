fun AcountData(
    nombre: String,
    telefono: String,
    genero: String,
    edad: Int,
    correo: String,
    descripcionPerfil: String,
    ciudad: String,
    fechaCreacion: String,
    password: String
) {

    UserInfo.nombre = nombre
    UserInfo.telefono = telefono
    UserInfo.genero = genero
    UserInfo.edad = edad
    UserInfo.correo = correo
    UserInfo.descripcionPerfil = descripcionPerfil
    UserInfo.ciudad = ciudad
    UserInfo.fechaCreacion = fechaCreacion
    UserInfo.password = password
}