package dev.redfox.noshassignment.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.redfox.noshassignment.model.PickerStyle
import dev.redfox.noshassignment.model.TimeModel
import dev.redfox.noshassignment.ui.theme.LightPurple
import dev.redfox.noshassignment.ui.theme.Purple
import dev.redfox.noshassignment.ui.theme.Transparent_gray
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview
@Composable
fun VerticalTimeSelectorSlider():TimeModel{

        var hour by remember{
            mutableStateOf("06")
        }
        var minute by remember {
            mutableStateOf("30")
        }

        Box(
            modifier = Modifier.run {
                background(LightPurple)
                    .fillMaxWidth()
            },
            contentAlignment = Alignment.Center
        ){

            Row(
                modifier = Modifier
                    .height(284.dp)
                    .padding(horizontal = 30.dp),

                ){


                VerticalPicker(
                    list = (1..12).toList(),
                    selectedValue = hour.toInt(),
                    onValueChanged = {
                        hour = it.toString()
                    },
                    modifier = Modifier
                        .height(284.dp)
                        .weight(1f),
                    showAmount = 5,
                    currentValue = hour.toInt()
                )
                VerticalPicker(
                    list = (1..60).toList(),
                    selectedValue = minute.toInt(),
                    onValueChanged = {
                        minute = it.toString()
                    },
                    modifier = Modifier
                        .height(284.dp)
                        .weight(1f),
                    showAmount = 5,
                    currentValue = minute.toInt()
                )

            }

        }
        return TimeModel(hour,minute)
    }


    @Composable
    fun <T>VerticalPicker(
        list:List<T>,
        showAmount:Int = 5,
        selectedValue:T,
        modifier: Modifier = Modifier,
        style: PickerStyle = PickerStyle(),
        onValueChanged:(T)->Unit,
        currentValue:Int
    ) {
        var userHasInteracted by remember { mutableStateOf(false) }
        val correctionValue by remember { mutableStateOf(if (list.size % 2 == 0) 1 else 0) }

        val listCount by remember {
            mutableStateOf(list.size)
        }

        val spacePerItem = with(LocalDensity.current) { (184.dp / (showAmount - 1)).toPx() }
        val initialIndex = list.indexOf(selectedValue)
        val initialOffset = (initialIndex - listCount / 2) * spacePerItem

        var dragStartedY by remember {
            mutableStateOf(0f)
        }

        var currentDragY by remember {
            mutableStateOf(initialOffset)
        }

        var oldY by remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(key1 = selectedValue) {
            if (!userHasInteracted) {
                onValueChanged(selectedValue)
            }
        }
        val selectedIndex = list.indexOf(selectedValue)
        val visibleRange = (selectedIndex - 1)..(selectedIndex + 1)

        Canvas(
            modifier = modifier
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            dragStartedY = offset.y

                        },

                        onDragEnd = {
                            userHasInteracted = true
                            val spacePerItem = size.height / showAmount
                            val rest = currentDragY % spacePerItem

                            val roundUp = abs(rest / spacePerItem).roundToInt() == 1
                            val newY = if (roundUp) {
                                if (rest < 0) {
                                    currentDragY + abs(rest) - spacePerItem
                                } else {
                                    currentDragY - rest + spacePerItem
                                }
                            } else {
                                if (rest < 0) {
                                    currentDragY + abs(rest)
                                } else {
                                    currentDragY - rest
                                }
                            }
                            currentDragY = newY.coerceIn(
                                minimumValue = -(listCount / 2f) * spacePerItem,
                                maximumValue = (listCount / 2f - correctionValue) * spacePerItem
                            )
                            val index = (listCount / 2) + (currentDragY / spacePerItem).toInt()
                            onValueChanged(list[index])
                            oldY = currentDragY
                        },
                        onDrag = { change, _ ->

                            val changeY = change.position.y
                            val newY = oldY + (dragStartedY - changeY)
                            val spacePerItem = size.height / showAmount
                            currentDragY = newY.coerceIn(
                                minimumValue = -(listCount / 2f) * spacePerItem,
                                maximumValue = (listCount / 2f - correctionValue) * spacePerItem
                            )
                            val index = (listCount / 2) + (currentDragY / spacePerItem).toInt()
                            onValueChanged(list[index])
                        }
                    )
                }
        ) {

            val spaceForEachItem = (size.height / showAmount)

            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                textAlign = android.graphics.Paint.Align.CENTER
                isFakeBoldText = true
            }

            for (i in 0 until listCount) {
                if (i in visibleRange) {
                    val currentY = i * spaceForEachItem - currentDragY -
                            ((listCount - 1 + correctionValue - showAmount) / 2 * spaceForEachItem)

                    val isFirstOrLastVisible = i == visibleRange.first || i == visibleRange.last
                    val isSelected = list[i] == selectedValue && userHasInteracted

                    paint.apply {
                        textSize = if (isSelected) {
                            style.highlightedTextSize.toPx()

                        } else if (isFirstOrLastVisible) {

                            style.textSize.toPx() * 0.8f
                        } else {
                            style.textSize.toPx()
                        }

                        color = if (isSelected) {
                            style.highlightColor.toArgb()
                        } else if (isFirstOrLastVisible) {

                            Purple.toArgb()
                        } else {
                            style.textColor.toArgb()
                        }
                    }

                    drawContext.canvas.nativeCanvas.apply {
//                    val x = style.lineLength + 5.dp.toPx() + style.textSize.toPx()
                        val x = (size.width/2)
                        val y = currentY + style.textSize.toPx() / 2

                        drawText(
                            list[i].toString(),
                            x,
                            y,
                            paint
                        )
                    }

                }
            }
        }

        LaunchedEffect(key1 = selectedValue) {
            // This will now highlight the initial values as selected
            userHasInteracted = true
            onValueChanged(selectedValue)
        }

    }
