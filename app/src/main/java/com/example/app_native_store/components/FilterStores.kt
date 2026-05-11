package com.example.app_native_store.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FilterChips(
    types: List<String>,
    selectedType: String?,
    onTypeSelected: (String?) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        items(types) { type ->

            val isSelected = type == selectedType

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (isSelected) Color(0xFF0D47A1)
                        else Color(0xFFE0E0E0)
                    )
                    .clickable {
                        onTypeSelected(
                            if (isSelected) null else type
                        )
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = type,
                    color = if (isSelected) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}