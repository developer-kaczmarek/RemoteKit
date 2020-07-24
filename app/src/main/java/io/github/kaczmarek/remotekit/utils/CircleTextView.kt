package io.github.kaczmarek.remotekit.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import io.github.kaczmarek.remotekit.R
import kotlin.math.min

class CircleTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var circleShadowWidth = 10f
    private var circleRadius = 0f

    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    init {
        gravity = Gravity.CENTER
        setTextColor(ContextCompat.getColor(context, R.color.colorFont))
        typeface = ResourcesCompat.getFont(context, R.font.pacifico)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val size = min(width, height)

        setMeasuredDimension(size, size)

        circleRadius = size / 2.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        setShadowBackground(measuredWidth, measuredHeight)
        canvas.drawCircle(circleRadius, circleRadius, circleRadius - circleShadowWidth, paint)
        super.onDraw(canvas)
    }

    private fun setShadowBackground(shadowWidth: Int, shadowHeight: Int) {
        val bitmap = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shadowRect = RectF(
            circleShadowWidth,
            circleShadowWidth,
            shadowWidth - circleShadowWidth,
            shadowHeight - circleShadowWidth
        )

        if (!isInEditMode) {
            paint.setShadowLayer(
                circleShadowWidth,
                0f,
                0f,
                ContextCompat.getColor(context, R.color.colorShadow)
            )
        }
        canvas.drawRoundRect(shadowRect, 360f, 360f, paint)

        val drawable = BitmapDrawable(resources, bitmap)
        background = drawable
    }
}