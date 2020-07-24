package io.github.kaczmarek.remotekit.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import io.github.kaczmarek.remotekit.R
import kotlin.math.abs
import kotlin.math.min

class PulsatingDrawable(context: Context) : Drawable() {

    private var currentAnimationValue = 0f

    private var currentRadius = 10f

    private val paint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 5f
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    override fun draw(canvas: Canvas) {
        if (bounds.exactCenterX() != 0f && bounds.exactCenterY()!= 0f) {
            canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), currentRadius, paint)
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun setAlpha(alpha: Int) {}

    override fun getOpacity(): Int = PixelFormat.TRANSPARENT

    fun update(bound: Rect, @FloatRange(from = 0.0, to = 1.0) animationValue: Float) {
        paint.alpha = (abs(1 - animationValue) * 255).toInt()
        bounds = bound
        currentAnimationValue = animationValue
        currentRadius = (min(bounds.width(), bounds.height()) / 2).toFloat() * currentAnimationValue
        invalidateSelf()
    }
}