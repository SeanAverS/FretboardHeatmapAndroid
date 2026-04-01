package com.example.fretboardheatmap

object TopMenuKeyMatcher {
    /**
     * Display same key for new top menu choice
     * Keeps heatmap and bottom menu display consistent for user
     * @param currentOption current chord or scale
     * @param targetMenu top menu choice
     */
    fun getMatch(currentOption: String, targetMenu: TopMenuChoice): String {
        return when (targetMenu) {
            TopMenuChoice.SCALES -> when (currentOption) {
                "Major" -> "Maj Pentatonic"
                "Minor" -> "Min Pentatonic"
                else -> "Maj Pentatonic"
            }
            TopMenuChoice.CHORDS -> when (currentOption) {
                "Ionian" -> "Major"
                "Maj Pentatonic" -> "Major"
                "Min Pentatonic" -> "Minor"
                else -> "Major"
            }
        }
    }
}