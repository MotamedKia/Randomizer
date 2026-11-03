package com.example.randomnumber.logics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun Dice(
    modifier: Modifier = Modifier, sides: Int = 6, isRolling: Boolean,
){
    var result by remember { mutableStateOf<Int?>(null) }

    val rotation = remember { Animatable(0f) }

    val alpha by animateFloatAsState(
        targetValue = if (result != null) 1f else 0f,
        animationSpec = tween(600),
        label = "diceFade"
    )

    LaunchedEffect(isRolling) {
        if (isRolling) {
            result = null

            rotation.animateTo(
                targetValue = rotation.value + 720f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = FastOutSlowInEasing
                )
            )

            result = Random.nextInt(1, sides + 1)
        }
    }
    require(sides == 6 || sides == 20) {
        "Only D6 and D20 dice are supported."
    }
    var isRolling by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .size(110.dp)
            .graphicsLayer {
                rotationZ = rotation.value
            },
        contentAlignment = Alignment.Center
    ) {

        // D6: Cubic appearance
        if (sides == 6) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFFFDAD6),
                        shape = RoundedCornerShape(18.dp)
                    )
            )
        }

        // D20: Polygonal appearance
        else {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val center = Offset(
                    x = size.width / 2,
                    y = size.height / 2
                )

                val radius = size.minDimension * 0.46f

                // Create a regular pentagon
                val vertices = List(5) { index ->
                    val angle = Math.toRadians(
                        (index * 72.0) - 90.0
                    )

                    Offset(
                        x = center.x + radius * cos(angle).toFloat(),
                        y = center.y + radius * sin(angle).toFloat()
                    )
                }

                val polygon = Path().apply {
                    moveTo(vertices.first().x, vertices.first().y)

                    vertices.drop(1).forEach {
                        lineTo(it.x, it.y)
                    }

                    close()
                }

                // Polygon background
                drawPath(
                    path = polygon, color = Color(0xFFFFDAD6)
//                    color = MaterialTheme.colorScheme.primaryContainer
                )

                // Polygon outline
                drawPath(
                    path = polygon,
                    color = Color(0xFFFFDAD6),
                    style = Stroke(width = 3.dp.toPx())
                )

                // Internal facets
                vertices.forEach { vertex ->
                    drawLine(
                        color = Color(0xFFFFDAD6),
                        start = center,
                        end = vertex,
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }

        // Dice number
        Text(
            text = result?.toString() ?: "",
            modifier = Modifier.alpha(alpha),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF18120B)
        )
    }
}