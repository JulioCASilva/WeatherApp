package com.example.findinglogs.view.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.view.compose.theme.WeatherColors

@Composable
fun AddCityButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(WeatherColors.CardGlass)
            .border(1.dp, WeatherColors.CardBorder, RoundedCornerShape(28.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Add, null, tint = WeatherColors.TextPrimary)
        Spacer(Modifier.width(8.dp))
        Text("Adicionar cidade", color = WeatherColors.TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}