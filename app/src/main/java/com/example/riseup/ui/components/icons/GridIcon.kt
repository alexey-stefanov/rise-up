package com.example.riseup.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Grid: ImageVector
    get() {
        if (_Grid != null) {
            return _Grid!!
        }
        _Grid = ImageVector.Builder(
            name = "Grid",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12.5f, 2f)
                horizontalLineTo(8f)
                verticalLineTo(7f)
                horizontalLineTo(13f)
                verticalLineTo(2.5f)
                curveTo(13f, 2.2239f, 12.7761f, 2f, 12.5f, 2f)
                close()
                moveTo(13f, 8f)
                horizontalLineTo(8f)
                verticalLineTo(13f)
                horizontalLineTo(12.5f)
                curveTo(12.7761f, 13f, 13f, 12.7761f, 13f, 12.5f)
                verticalLineTo(8f)
                close()
                moveTo(7f, 7f)
                verticalLineTo(2f)
                horizontalLineTo(2.5f)
                curveTo(2.2239f, 2f, 2f, 2.2239f, 2f, 2.5f)
                verticalLineTo(7f)
                horizontalLineTo(7f)
                close()
                moveTo(2f, 8f)
                verticalLineTo(12.5f)
                curveTo(2f, 12.7761f, 2.2239f, 13f, 2.5f, 13f)
                horizontalLineTo(7f)
                verticalLineTo(8f)
                horizontalLineTo(2f)
                close()
                moveTo(2.5f, 1f)
                curveTo(1.6716f, 1f, 1f, 1.6716f, 1f, 2.5f)
                verticalLineTo(12.5f)
                curveTo(1f, 13.3284f, 1.6716f, 14f, 2.5f, 14f)
                horizontalLineTo(12.5f)
                curveTo(13.3284f, 14f, 14f, 13.3284f, 14f, 12.5f)
                verticalLineTo(2.5f)
                curveTo(14f, 1.6716f, 13.3284f, 1f, 12.5f, 1f)
                horizontalLineTo(2.5f)
                close()
            }
        }.build()
        return _Grid!!
    }

private var _Grid: ImageVector? = null
