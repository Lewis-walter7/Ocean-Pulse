package com.licoding.oceanpulse.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MinimalDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(6.dp)
            ){
                item {
                    Text(
                        text = "50% of oxygen on the planet \n comes from oceans.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    Column {
                        Text(
                            text = "1. Photosynthesis in Oceans:",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Phytoplankton, microscopic algae found in large quantities in the ocean, play a crucial role in producing oxygen. Through photosynthesis, phytoplankton use sunlight, carbon dioxide, and nutrients to create organic compounds and release oxygen into the water"
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Column {
                        Text(
                            text = "2. Global Oxygen Production:",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "While terrestrial plants also contribute significantly to oxygen production, the vastness of the oceans and the abundance of phytoplankton make marine ecosystems a major source of atmospheric oxygen.\n" +
                                    "Estimates suggest that marine plants, including phytoplankton, contribute more than half of the world's oxygen supply. Some studies propose figures as high as 70% or more."
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Column {
                        Text(
                            text = "3. Carbon Sequestration:",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "In addition to producing oxygen, marine ecosystems, especially seaweed and seagrasses, play a role in carbon sequestration. They absorb and store carbon dioxide, helping regulate the Earth's climate and reducing the impact of greenhouse gases."
                        )
                    }
                }
            }
        }
    }
}
