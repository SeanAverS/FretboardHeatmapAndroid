package com.example.fretboardheatmap

/**
 * Prepare musical notes for FretLabels
 */
object NoteAlphabet {
    private val notes = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
    private val openStringIndex = listOf(4, 11, 7, 2, 9, 4) // High E to Low E

    /**
     * Determine musical note for current position
     *  Formula: `(Open String Position + Current Fret) % 12`
     *  - Modulo keeps the value inside notes[]
     * @param string the current string
     * @param fret the current fret
     */
    fun getNoteName(string: Int, fret: Int): String {
        if (string !in openStringIndex.indices) return ""
        val notePosition = (openStringIndex[string] + fret) % 12
        return notes[notePosition]
    }
}