import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectComp(
    label: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    var selected by remember {
        mutableStateOf(options.firstOrNull() ?: "")
    }

    LaunchedEffect(Unit) {
        if (selected.isNotEmpty()) {
            onValueChange(selected)
        }
    }

    Column {

        Text(text = label)

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp)
            ,
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(selected, modifier = Modifier.fillMaxWidth())
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = Color.Gray, width = 1.dp)
            ,
            shape = RoundedCornerShape(4.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    text = { Text(option) },
                    onClick = {
                        selected = option
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}