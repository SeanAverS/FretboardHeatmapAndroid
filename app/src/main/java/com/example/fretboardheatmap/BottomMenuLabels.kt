package com.example.fretboardheatmap

object BottomMenuLabels {

    /**
     * Provides the bottom label menus
     * @param root root note choice
     * @param topMenu top menu choice
     * @param dropdownChoice the right bottom menu labels
     */
    fun getLabels(
        root: String,
        topMenu: TopMenuChoice?,
        dropdownChoice: String
    ): String {
        return if (minorKeySelected(topMenu, dropdownChoice)) {
            "${root}m"
        } else {
            root
        }
    }

    /**
     * Prepare bottom menu minor labels
     * @param topMenu top menu choice
     * @param dropdownChoice top menu dropdown choice
     */
    private fun minorKeySelected(topMenu: TopMenuChoice?, dropdownChoice: String): Boolean {
        if (topMenu == null) return false

        val isMinor = dropdownChoice.startsWith("Min", ignoreCase = true)

        return isMinor
    }
}