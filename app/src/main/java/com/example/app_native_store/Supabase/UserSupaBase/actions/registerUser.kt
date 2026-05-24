import com.example.app_native_store.network.SUPABASE_URL
import com.example.app_native_store.network.supabaseClient
import com.google.gson.Gson
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import java.util.UUID

suspend fun registrarUsuario(
    nombre: String,
    correo: String,
    password: String,
    rol: String,
    telefono: String = "",
    genero: String = "",
    edad: Int? = null,
    descripcionPerfil: String = "",
    ciudad: String = ""
): Pair<String?, String?> {
    val nuevoId = UUID.randomUUID().toString()
    val nuevoUsuario = mapOf(
        "id"                 to nuevoId,
        "nombre"             to nombre,
        "correo"             to correo,
        "password"           to password,
        "rol"                to rol,
        "telefono"           to telefono.ifBlank { null },
        "genero"             to genero.ifBlank { null },
        "edad"               to edad,
        "descripcion_perfil" to descripcionPerfil.ifBlank { null },
        "ciudad"             to ciudad.ifBlank { null }
    )
    val response = supabaseClient.post("${SUPABASE_URL}/rest/v1/usuarios") {
        contentType(ContentType.Application.Json)
        header("Prefer", "return=minimal")
        setBody(Gson().toJson(nuevoUsuario))
    }
    val status = response.status.value
    val body   = response.bodyAsText()
    return if (status == 201 || status == 200) Pair(nuevoId, null)
    else Pair(null, "Error $status: $body")
}