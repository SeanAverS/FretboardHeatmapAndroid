package com.example.fretboardheatmap

import androidx.compose.ui.unit.dp


/**
 * Provides data for guitar frets, strings, and root notes.
 */
object GuitarSpecs {
    // guitar frets
    val frets = listOf(90.dp, 85.dp, 80.dp, 75.dp, 71.dp, 67.dp, 63.dp, 60.dp, 56.dp, 53.dp, 50.dp, 47.dp)

    // guitar strings
    val strings = listOf(0.8.dp, 1.2.dp, 1.8.dp, 2.5.dp, 3.2.dp, 4.0.dp)

    // root notes
    val roots = listOf("G", "D", "C", "E", "A")
}