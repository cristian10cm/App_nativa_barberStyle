import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun ButtonComponent (textBtn: String, onClick:()-> Unit){
    Button(
        modifier = Modifier.fillMaxWidth()
            .border(width = 0.dp,color = Color.Transparent)
        ,
        onClick=onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF102922)),
        shape = RoundedCornerShape(5.dp),


    ) {
        Text(textBtn,color = Color.White)
    }
}
