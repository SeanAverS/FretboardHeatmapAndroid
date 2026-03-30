package com.example.fretboardheatmap

/**
 * Manages fret note and finger positions
 */
object FretPositions {

    // scales map
    val scales: Map<String, Map<String, Map<Int, List<Int>>>> = mapOf(
        "Min Pentatonic" to mapOf(
            "G" to mapOf(0 to listOf(1, 3, 6, 8, 10, 13), 1 to listOf(1, 3, 6, 8, 11, 13), 2 to listOf(3, 5, 7, 10, 12), 3 to listOf(3, 5, 8, 10, 12), 4 to listOf(1, 3, 5, 8, 10, 13), 5 to listOf(1, 3, 6, 8, 10, 13)),
            "D" to mapOf(0 to listOf(1, 3, 5, 8, 10), 1 to listOf(1, 3, 6, 8, 10), 2 to listOf(2, 5, 7, 10, 12), 3 to listOf(0, 3, 5, 7, 10, 12), 4 to listOf(0, 3, 5, 8, 10, 12), 5 to listOf(1, 3, 5, 8, 10)),
            "C" to mapOf(0 to listOf(1, 3, 6, 8, 11, 13), 1 to listOf(1, 4, 6, 8, 11, 13), 2 to listOf(0, 3, 5, 8, 10, 12), 3 to listOf(1, 3, 5, 8, 10, 13), 4 to listOf(1, 3, 6, 8, 10, 13), 5 to listOf(1, 3, 6, 8, 11, 13)),
            "E" to mapOf(0 to listOf(0, 3, 5, 7, 10, 12), 1 to listOf(0, 3, 5, 8, 10, 12), 2 to listOf(0, 2, 4, 7, 9, 12), 3 to listOf(0, 2, 5, 7, 9, 12), 4 to listOf(0, 2, 5, 7, 10, 12), 5 to listOf(0, 3, 5, 7, 10, 12)),
            "A" to mapOf(0 to listOf(0, 3, 5, 8, 10, 12), 1 to listOf(1, 3, 5, 8, 10, 13), 2 to listOf(2, 5, 7, 9, 12), 3 to listOf(2, 5, 7, 10, 12), 4 to listOf(0, 3, 5, 7, 10, 12), 5 to listOf(0, 3, 5, 8, 10, 12))
        ),
        "Maj Pentatonic" to mapOf(
            "G" to mapOf(0 to listOf(0, 3, 5, 7, 10, 12), 1 to listOf(0, 3, 5, 8, 10, 12), 2 to listOf(0, 2, 4, 7, 9, 12), 3 to listOf(0, 2, 5, 7, 9, 12), 4 to listOf(0, 2, 5, 7, 10, 12), 5 to listOf(0, 3, 5, 7, 10, 12)),
            "D" to mapOf(0 to listOf(0, 2, 5, 7, 10, 12), 1 to listOf(0, 3, 5, 7, 10, 12), 2 to listOf(2, 4, 7, 9, 11), 3 to listOf(0, 2, 4, 7, 9, 12), 4 to listOf(0, 2, 5, 7, 9, 12), 5 to listOf(0, 2, 5, 7, 10, 12)),
            "C" to mapOf(0 to listOf(0, 3, 5, 8, 10, 12), 1 to listOf(1, 3, 5, 8, 10), 2 to listOf(0, 2, 5, 7, 9, 12), 3 to listOf(0, 2, 5, 7, 10, 12), 4 to listOf(3, 5, 7, 10, 12), 5 to listOf(0, 3, 5, 8, 10, 12)),
            "E" to mapOf(0 to listOf(0, 2, 4, 7, 9, 12), 1 to listOf(2, 5, 7, 9, 12), 2 to listOf(1, 4, 6, 9, 11), 3 to listOf(2, 4, 6, 9, 11), 4 to listOf(2, 4, 7, 9, 11), 5 to listOf(2, 4, 7, 9, 12)),
            "A" to mapOf(0 to listOf(2, 5, 7, 9, 12), 1 to listOf(2, 5, 7, 10, 12), 2 to listOf(2, 4, 6, 9, 11), 3 to listOf(2, 4, 7, 9, 11), 4 to listOf(2, 4, 7, 9, 12), 5 to listOf(2, 5, 7, 9, 12))
        ),
        "Ionian" to mapOf(
            "G" to mapOf(0 to listOf(2, 3, 5, 7, 8, 10, 12), 1 to listOf(1, 3, 5, 7, 8, 10, 12), 2 to listOf(2, 4, 5, 7, 9, 11, 12), 3 to listOf(2, 4, 5, 7, 9, 10, 12), 4 to listOf(2, 3, 5, 7, 9, 10, 12), 5 to listOf(2, 3, 5, 7, 8, 10, 12)),
            "D" to mapOf(0 to listOf(2, 3, 5, 7, 9, 10, 12), 1 to listOf(2, 3, 5, 7, 8, 10, 12), 2 to listOf(2, 4, 6, 7, 9, 11, 12), 3 to listOf(2, 4, 5, 7, 9, 11, 12), 4 to listOf(2, 4, 5, 7, 9, 10, 12), 5 to listOf(2, 3, 5, 7, 9, 10, 12)),
            "C" to mapOf(0 to listOf(1, 3, 5, 7, 8, 10, 12), 1 to listOf(1, 3, 5, 6, 8, 10, 12), 2 to listOf(2, 4, 5, 7, 3, 4, 12), 3 to listOf(2, 3, 5, 7, 9, 10, 12), 4 to listOf(2, 3, 5, 7, 8, 10, 12), 5 to listOf(1, 3, 5, 7, 8, 10, 12)),
            "E" to mapOf(0 to listOf(2, 4, 5, 7, 9, 11, 12), 1 to listOf(2, 4, 5, 7, 9, 10, 12), 2 to listOf(1, 2, 4, 6, 8, 9, 11), 3 to listOf(1, 2, 4, 6, 7, 9, 11), 4 to listOf(2, 4, 6, 7, 9, 11, 12), 5 to listOf(2, 4, 5, 7, 9, 11, 12)),
            "A" to mapOf(0 to listOf(2, 4, 5, 7, 9, 10, 12), 1 to listOf(2, 3, 5, 7, 9, 10, 12), 2 to listOf(1, 2, 4, 6, 7, 9, 11), 3 to listOf(2, 4, 6, 7, 9, 11, 12), 4 to listOf(2, 4, 5, 7, 9, 11, 12), 5 to listOf(2, 4, 5, 7, 9, 10, 12))
        )
    )

    // chords map
    val chords: Map<String, Map<String, Map<Int, List<Int>>>> = mapOf(
        "Major" to mapOf(
            "G" to mapOf(0 to listOf(3), 4 to listOf(2), 5 to listOf(3)),
            "D" to mapOf(0 to listOf(2), 1 to listOf(3), 2 to listOf(2)),
            "C" to mapOf(1 to listOf(1), 3 to listOf(2), 4 to listOf(3)),
            "E" to mapOf(2 to listOf(1), 3 to listOf(2), 4 to listOf(2)),
            "A" to mapOf(1 to listOf(2), 2 to listOf(2), 3 to listOf(2))
        ),
        "Minor" to mapOf(
            "A" to mapOf(1 to listOf(1), 2 to listOf(2), 3 to listOf(2)),
            "E" to mapOf(3 to listOf(2), 4 to listOf(2)),
            "D" to mapOf(0 to listOf(1), 1 to listOf(3), 2 to listOf(2)),
            "G" to mapOf(0 to listOf(3), 1 to listOf(3), 2 to listOf(3), 3 to listOf(5), 4 to listOf(5), 5 to listOf(3)),
            "C" to mapOf(0 to listOf(3), 1 to listOf(4), 2 to listOf(5), 3 to listOf(5))
        )
    )

    // finger numbers map
    val fingerNumbers: Map<String, Map<String, Map<String, String>>> = mapOf(
        "Major" to mapOf(
            "G" to mapOf("5,3" to "2", "4,2" to "1", "0,3" to "3"),
            "D" to mapOf("2,2" to "1", "1,3" to "3", "0,2" to "2"),
            "C" to mapOf("4,3" to "3", "3,2" to "2", "1,1" to "1"),
            "E" to mapOf("2,1" to "1", "3,2" to "3", "4,2" to "2"),
            "A" to mapOf("3,2" to "1", "2,2" to "2", "1,2" to "3")
        ),
        "Minor" to mapOf(
            "A" to mapOf("1,1" to "1", "2,2" to "3", "3,2" to "2"),
            "E" to mapOf("3,2" to "2", "4,2" to "1"),
            "D" to mapOf("0,1" to "1", "1,3" to "3", "2,2" to "2"),
            "C" to mapOf("3,5" to "3", "2,5" to "4", "1,4" to "2", "0,3" to "1"),
            "G" to mapOf("5,3" to "1", "4,5" to "3", "3,5" to "4", "2,3" to "1", "1,3" to "1", "0,3" to "1")
        )
    )

//    /**
//     * Prepare dictionaries for top menu dropdown
//     * @param forTopMenu top menu choice
//     */
//    fun dropdownChoices(forTopMenu: TopMenuChoice): List<String> {
//        return when (forTopMenu) {
//            TopMenuChoice.CHORDS -> chords.keys.sorted()
//            TopMenuChoice.SCALES -> scales.keys.sorted()
//        }
//    }

    /**
     * Prepare fret notes for heatmap
     * @param topMenu top menu choice
     * @param dropdownChoice dropdown option choice
     * @param root root note choice
     */
    fun getFretMap(
        topMenu: TopMenuChoice?,
        dropdownChoice: String,
        root: String
    ): Map<Int, List<Int>> {
        if (topMenu == null) return emptyMap()

        return when (topMenu) {
            TopMenuChoice.CHORDS -> chords[dropdownChoice]?.get(root) ?: emptyMap()
            TopMenuChoice.SCALES -> scales[dropdownChoice]?.get(root) ?: emptyMap()
        }
    }

    /**
     * Get finger numbers for chords
     * @param dropdownChoice dropdown option choice
     * @param root root note choice
     * @param string the current string
     * @param fret the current fret
     */
    fun getFingerNumber(dropdownChoice: String, root: String, string: Int, fret: Int): String {
        val key = "$string,$fret"
        return fingerNumbers[dropdownChoice]?.get(root)?.get(key) ?: ""
    }
}