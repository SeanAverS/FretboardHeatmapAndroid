package com.example.fretboardheatmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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

                        Spacer(modifier = Modifier.height(3.3.dp))

                        // guitar neck
                        Box(contentAlignment = Alignment.Center) {
                            GuitarNeckView()
                            GuitarStringsView()
                        }
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
            .padding(top = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // center label and top menu buttons
        Spacer(modifier = Modifier.weight(1f))

        // label and top menu buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelToggleButton(
                isActive = labelsButton,
                onClick = onLabelsButtonToggle
            )

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
 * Style and render label and top menu buttons
 * @param title of button
 * @param isHighlighted current state of button
 * @param onClick toggle MenuText functions
 */
@Composable
private fun MenuText(title: String, isHighlighted: Boolean, onClick: () -> Unit) {
    Text(
        text = title,
        color = if (isHighlighted) Color.Yellow else Color.White,
        modifier = Modifier
            // remove default ripple effect
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() }
    )
}

/**
 * MenuText helper function for "LABELS"
 * @param isActive current state of button
 * @param onClick toggle MenuText functions
 */
@Composable
fun LabelToggleButton(isActive: Boolean, onClick: () -> Unit) {
    MenuText(title = "LABELS", isHighlighted = isActive, onClick = onClick)
}

/**
 * MenuText helper function for "CHORDS" and "SCALES"
 * @param title of button
 * @param isSelected current state of selected button
 * @param onClick toggle MenuText functions
 */
@Composable
fun TopMenuButton(title: String, isSelected: Boolean, onClick: () -> Unit) {
    MenuText(title = title, isHighlighted = isSelected, onClick = onClick)
}

/**
 * Generate fretboard wood and frets for guitar neck
 */
@Composable
fun GuitarNeckView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(265.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // fretboard wood
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A0005), // Start
                            Color(0xFF40261A), // Middle
                            Color(0xFF1A0D05)  // End
                        )
                    )
                )
        )

        // frets (1-12)
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // nut
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFE6E6E6))
            )

            // frets
            GuitarSpecs.frets.forEachIndexed { index, fretWidth ->
                // inlays
                Box(
                    modifier = Modifier
                        .weight(fretWidth.value) // keep inlay inside current fret
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    FretInlays(index)
                }

                // wire
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)
                )
            }
        }
    }
}

/**
 * Fret inlay styling (grey circles)
 * @param index current fret on the guitar neck
 */
@Composable
private fun FretInlays(index: Int) {
    val inlayColor = Color(0xFFB3B3B3)

    if (listOf(2, 4, 6, 8).contains(index)) { // single dot
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(inlayColor, shape = CircleShape)
        )
    } else if (index == 11) { // double dot
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(55.dp, Alignment.CenterVertically)
        ) {
            Box(modifier = Modifier.size(20.dp).background(inlayColor, shape = CircleShape))
            Box(modifier = Modifier.size(20.dp).background(inlayColor, shape = CircleShape))
        }
    }
}

/**
 * Generate guitar strings on fretboard
 */
@Composable
fun GuitarStringsView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(265.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.weight(1f))
        GuitarSpecs.strings.forEach { thickness ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(thickness)
                    .background(Color(0xFF999999))
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}