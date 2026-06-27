package com.example.findinglogs.view.compose.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.view.compose.theme.WeatherColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityModal(
    onDismiss: () -> Unit,
    onAddCity: (City) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val filtered = remember(query) {
        CityCatalog.cities.filter { it.name.contains(query.trim(), ignoreCase = true) }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = WeatherColors.BackgroundMid,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
        ) {
            Text("Adicionar cidade", color = WeatherColors.TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("${filtered.size} cidades disponíveis", color = WeatherColors.TextSecondary, fontSize = 14.sp)

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar cidade...") },
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = WeatherColors.TextPrimary,
                    unfocusedTextColor = WeatherColors.TextPrimary,
                    focusedBorderColor = WeatherColors.CardBorder,
                    unfocusedBorderColor = WeatherColors.CardBorder,
                    focusedLeadingIconColor = WeatherColors.TextSecondary,
                    unfocusedLeadingIconColor = WeatherColors.TextSecondary,
                    focusedPlaceholderColor = WeatherColors.TextSecondary,
                    unfocusedPlaceholderColor = WeatherColors.TextSecondary
                )
            )

            Spacer(Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filtered, key = { it.latLon }) { city ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .border(1.dp, WeatherColors.CardBorder, RoundedCornerShape(16.dp))
                            .background(WeatherColors.CardGlass, RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(city.name, color = WeatherColors.TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("Brasil", color = WeatherColors.TextSecondary, fontSize = 13.sp)
                        }
                        IconButton(
                            onClick = { onAddCity(city); onDismiss() },
                            modifier = Modifier.size(40.dp).background(WeatherColors.IconCircle, CircleShape)
                        ) {
                            Icon(Icons.Filled.Add, "Adicionar", tint = WeatherColors.TextPrimary)
                        }
                    }
                }
            }
        }
    }
}