package com.example.fretboardheatmap

/**
 * Labels non-root fretboard notes(for scales) and finger numbers(for chords)
 */
object FretLabels {

    /**
     * Prepare fret labels for heatmap
     * @param topMenu "CHORDS" or "SCALES" choice
     * @param root root note choice
     * @param dropdownChoice dropdown option choice
     * @param string the current string
     * @param fret the current fret
     */
    fun getLabels(
        topMenu: TopMenuChoice?,
        root: String,
        dropdownChoice: String,
        string: Int,
        fret: Int
    ): String {
        return if (topMenu == TopMenuChoice.SCALES) {
            NoteAlphabet.getNoteName(string, fret)
        } else { // CHORDS
            FretPositions.getFingerNumber(dropdownChoice, root, string, fret)
        }
    }
}