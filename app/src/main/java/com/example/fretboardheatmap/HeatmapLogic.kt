package com.example.fretboardheatmap

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Calculate and display fret positions on the fretboard
 */
@Composable
fun HeatmapLogic(
    selectedRoot: String?,
    topMenu: TopMenuChoice?,
    selectedDropdownOption: String,
    noteLabels: Boolean
) {
    if (selectedRoot == null || topMenu == null) return

    // animations for heatmap dots
    // parameters handle state when transitioning between keys
    AnimatedContent(
        targetState = Triple(selectedRoot, selectedDropdownOption, topMenu),
        transitionSpec = {
            val duration = 280

            slideInHorizontally( // new key
                animationSpec = tween(duration),
                initialOffsetX = { -it }
            ) + fadeIn(animationSpec = tween(duration)) togetherWith
                    slideOutHorizontally( // old key
                        animationSpec = tween(duration),
                        targetOffsetX = { it }
                    ) + fadeOut(animationSpec = tween(duration))
        },
        label = "FretboardTransition"
    ) { (currentRoot, currentDropdown, currentMenu) ->
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(265.dp)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.weight(1f))

                // display dots on each string
                repeat(6) { stringIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        HeatmapFrets(
                            string = stringIndex,
                            selectedRoot = currentRoot,
                            topMenu = currentMenu,
                            selectedDropdownOption = currentDropdown,
                            noteLabels = noteLabels
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f)) // center horizontally
                }
            }
        }
    }
}

/**
 * Prepare fret dots for the heatmap
 * @param string the current string
 * @param selectedRoot the selected root
 * @param topMenu current state of top menu button
 * @param selectedDropdownOption the selected dropdown choice
 * @param noteLabels current state of labels button
 */
@Composable
private fun HeatmapFrets(
    string: Int,
    selectedRoot: String,
    topMenu: TopMenuChoice,
    selectedDropdownOption: String,
    noteLabels: Boolean
) {
    // get fret positions and finger numbers
    val positions = FretPositions.getFretMap(topMenu, selectedDropdownOption, selectedRoot)

    val stringPositions = positions[string] ?: emptyList()

    // center each fret on current string
    Row(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.width(10.dp))

        // account for current fret thickness
        GuitarSpecs.frets.forEachIndexed { index, fretWeight ->
            val currentFretNumber = index + 1

            Box(
                modifier = Modifier
                    .weight(fretWeight.value)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                if (stringPositions.contains(currentFretNumber)) {
                    CenterNotes(
                        root = selectedRoot,
                        string = string,
                        fret = currentFretNumber,
                        topMenu = topMenu,
                        dropdown = selectedDropdownOption,
                        showLabels = noteLabels
                    )
                }
            }
            Spacer(modifier = Modifier.width(3.dp))
        }
    }
}

/**
 * Calculate center of fret dots and label positions
 * @param root the current root
 * @param string the current string
 * @param fret the current fret
 * @param topMenu current state of top menu button
 * @param dropdown the selected dropdown choice
 * @param showLabels current state of labels button
 */
@Composable
private fun CenterNotes(
    root: String,
    string: Int,
    fret: Int,
    topMenu: TopMenuChoice,
    dropdown: String,
    showLabels: Boolean
) {
        // because parent box(HeatmapFrets) has contentAlignment.Center, alignment math used in Swift not needed
        // NoteCircle gets auto centered in current fret
        NoteCircle(
            root = root,
            string = string,
            fret = fret,
            topMenu = topMenu,
            dropdown = dropdown,
            showLabels = showLabels
        )
}

/**
 * Determine the fret dots color and label state
 * @param root the current root
 * @param string the current string
 * @param fret the current fret
 * @param topMenu current state of top menu button
 * @param dropdown the selected dropdown choice
 * @param showLabels current state of labels button
 */
@Composable
fun NoteCircle(
    root: String,
    string: Int,
    fret: Int,
    topMenu: TopMenuChoice,
    dropdown: String,
    showLabels: Boolean
) {
    val isRoot = NoteAlphabet.getNoteName(string, fret) == root
    val labelText = FretLabels.getLabels(topMenu, root, dropdown, string, fret)

    Box( // dot size
        modifier = Modifier
            .size(24.dp)
            .background(if (isRoot) Color.Red else Color.Blue, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) { // labels
        if (showLabels && labelText.isNotEmpty()) {
            Text(
                text = labelText,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}