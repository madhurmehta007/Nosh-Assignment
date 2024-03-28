package dev.redfox.noshassignment.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.redfox.noshassignment.ui.theme.FoundationPurpleNormal
import dev.redfox.noshassignment.ui.theme.TextColor
import dev.redfox.noshassignment.ui.theme.Transparent_gray
import dev.redfox.noshassignment.ui.theme.gray

data class PickerStyle(
    val lineColor: Color = Transparent_gray,
    val lineLength: Float = 250f,
    val highlightedLineLength: Float = 250f,
    val textSize: TextUnit = 32.sp,
    val highlightedTextSize: TextUnit = 32.sp,
    val highlightColor: Color = TextColor,
    val highlightedLineColor: Color = FoundationPurpleNormal,
    val lineWidth: Float = 3f,
    val textColor: Color = gray,
    val distanceDecrement: Float = 35f,
    val minLineLength: Float = 20f
)
