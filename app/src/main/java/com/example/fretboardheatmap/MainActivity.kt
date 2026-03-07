package com.example.fretboardheatmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fretboardheatmap.ui.theme.FretboardHeatmapTheme

enum class TopMenuChoice { CHORDS, SCALES }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FretboardHeatmapTheme {
                // main states
                var labelsButton by remember { mutableStateOf(false) }
                var topMenuButton by remember { mutableStateOf<TopMenuChoice?>(null) } // "CHORDS" or "SCALES"
                var chordsDropdown by remember { mutableStateOf("Major") }
                var scalesDropdown by remember { mutableStateOf("Maj Pentatonic") }
                var isDropdownExpanded by remember { mutableStateOf(false) }

                // top menu area
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                        TopMenuArea(
                            labelsButton = labelsButton,
                            onLabelsButtonToggle = { labelsButton = !labelsButton },

                            topMenuButton = topMenuButton,
                            onTopMenuButtonSelect = {
                                topMenuButton = it // "CHORDS" or "SCALES"
                                isDropdownExpanded = false
                            },

                            // dropdown
                            dropdownTitle = if (topMenuButton == TopMenuChoice.CHORDS) chordsDropdown else scalesDropdown,

                            dropdownDisplay = isDropdownExpanded,
                            onDropdownToggle = { isDropdownExpanded = !isDropdownExpanded },

                            onDropdownSelect = { showOptions ->
                                if (topMenuButton == TopMenuChoice.CHORDS) chordsDropdown = showOptions
                                else scalesDropdown = showOptions
                                isDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * The Top Menu section containing navigation and dropdown controls
 * @param labelsButton current LABELS button visibility
 * @param onLabelsButtonToggle toggle LABELS button visibility
 * @param topMenuButton current "CHORDS" or "SCALES" button selection
 * @param onTopMenuButtonSelect toggle selected TopMenuButton functions
 * @param dropdownTitle initial text of dropdown
 * @param dropdownDisplay current dropdown display based on topMenuButton
 * @param onDropdownToggle toggle current dropdown visibility
 * @param onDropdownSelect process options for current dropdown
 */
@Composable
fun TopMenuArea(
    labelsButton: Boolean,
    onLabelsButtonToggle: () -> Unit,
    topMenuButton: TopMenuChoice?,
    onTopMenuButtonSelect: (TopMenuChoice) -> Unit,
    dropdownTitle: String,
    dropdownDisplay: Boolean,
    onDropdownToggle: () -> Unit,
    onDropdownSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // center label and top menu buttons
        Spacer(modifier = Modifier.weight(1f))

        // label and top menu buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TopMenuButton("LABELS", labelsButton, onLabelsButtonToggle)
            TopMenuButton("CHORDS", topMenuButton == TopMenuChoice.CHORDS) {
                onTopMenuButtonSelect(
                    TopMenuChoice.CHORDS
                )
            }
            TopMenuButton("SCALES", topMenuButton == TopMenuChoice.SCALES) {
                onTopMenuButtonSelect(
                    TopMenuChoice.SCALES
                )
            }
        }

        // dropdown
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) { // position by screens right edge
            if (topMenuButton != null) {
                // dropdown button
                Column {
                    Row(
                        modifier = Modifier
                            .padding(end = 30.dp)
                            .clickable { onDropdownToggle() },
                    ) {
                        Text(
                            text = dropdownTitle.uppercase(),
                            color = Color.Yellow,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                    }

                    // dropdown options
                    DropdownMenu(
                        expanded = dropdownDisplay,
                        onDismissRequest = { onDropdownToggle() },
                        modifier = Modifier.background(Color.Transparent),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        val options = if (topMenuButton == TopMenuChoice.CHORDS)
                            listOf("Major", "Minor")
                        else // SCALES
                            listOf("Ionian", "Min Pentatonic", "Maj Pentatonic")

                        // prepare right dropdown options
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option, color = Color.Black) },
                                onClick = { onDropdownSelect(option) }
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.size(1.dp))
            }
        }
    }
}

/**
 * Style and remember state of the selected top menu button
 * @param title selected top menu button ("CHORDS" or "SCALES")
 * @param isSelected state of selected top menu button
 * @param onClick toggle selected top menu button functions
 */
@Composable
fun TopMenuButton(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        color = if (isSelected) Color.Yellow else Color.White,
        modifier = Modifier
            // cancel ripple effect for color change effect
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    )
}