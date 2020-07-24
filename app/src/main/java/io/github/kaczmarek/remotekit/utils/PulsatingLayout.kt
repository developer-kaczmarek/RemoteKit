package io.github.kaczmarek.remotekit.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class PulsatingLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val circlesList = mutableListOf<PulsatingDrawable>()

    private val callback = DrawableCallback()

    private val animator = AnimatorSet()

    private val countOfCircle = 4

    private val durationForAnimation = 4000L

    override fun dispatchDraw(canvas: Canvas) {
        circlesList.forEach {
            it.draw(canvas)
        }
        super.dispatchDraw(canvas)
    }

    fun start() {
        stop()
        initAnimator()
        animator.start()
    }

    fun stop() {
        animator.cancel()
        animator.childAnimations.clear()
        circlesList.clear()
        postInvalidate()
    }

    private fun initAnimator() {
        val animatorList = mutableListOf<Animator>()
        for (i in 0 until countOfCircle) {
            val drawable = PulsatingDrawable(context)
            circlesList.add(drawable)

            if (i == 0) {
                drawable.callback = callback
            }

            val animator = ValueAnimator.ofFloat(0f, 1f).apply {
                val delay = i * durationForAnimation / countOfCircle

                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                startDelay = delay

                addUpdateListener {
                    val animValue = it.animatedValue as? Float ?: 0F
                    drawable.update(Rect(0, 0, width, height), animValue)
                }
            }
            animatorList.add(animator)
        }

        animator.duration = durationForAnimation
        animator.playTogether(animatorList)
    }

    private inner class DrawableCallback : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}

        override fun invalidateDrawable(who: Drawable) {
            postInvalidate()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
    }
}