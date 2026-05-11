import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable

fun InputComponent(value: String, onChange:(String)-> Unit, type: String = "text" ,label: String){
    val typeInput = when(type){
        "email" -> KeyboardType.Email
        "password" -> KeyboardType.Password
        "number" -> KeyboardType.Number
        else ->
            KeyboardType.Text
    }
    val visualTransformation = if (type == "password") {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    OutlinedTextField(
        value = value,
        label = { Text(text=label) },
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(keyboardType = typeInput),
        visualTransformation = visualTransformation,
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}