import com.google.gson.annotations.SerializedName

data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val telefono: String? = null,
    val genero: String? = null,
    val edad: Int? = null,
    val correo: String = "",
    @SerializedName("descripcion_perfil")
    val descripcionPerfil: String? = null,
    val ciudad: String? = null,
    @SerializedName("fecha_creacion")
    val fechaCreacion: String? = null,
    val password: String = "",
    val rol: String = ""
)