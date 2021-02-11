package com.dd.animationsmotionlayout

import android.animation.*
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

object Animations {

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        // This extension method listens for start/end events on an animation and disables
        // the given view for the entirety of that animation.

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }

    fun alphaAnimationOf(view: View) {
        ObjectAnimator.ofFloat(view, View.ALPHA, 0f).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }


    fun scalingAnimationOf(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.8f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.8f)
        ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }

    fun rollingAnimationOf(view: View) {
        ObjectAnimator.ofFloat(view, View.ROTATION, -360f, 0f).apply {
            duration = 1000
            disableViewDuringAnimation(view)
            start()
        }
    }


    fun changeBackgroundOf(view: View, context: Context) {

        ObjectAnimator.ofArgb(
            view.parent,
            "backgroundColor",
            ContextCompat.getColor(context, R.color.design_default_color_primary),
            ContextCompat.getColor(context, R.color.design_default_color_primary_dark)
        ).apply {
            duration = 500
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(view)
            start()
        }
    }


    fun rainyAnimationOf(view: View, context: Context) {
        val container = view.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = view.width.toFloat()
        var starH: Float = view.height.toFloat()

        val newStar = AppCompatImageView(context)
        newStar.setImageResource(R.drawable.ic_launcher_background)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)

        newStar.scaleX = Math.random().toFloat() * 1.5f + .1f
        newStar.scaleY = newStar.scaleX
        starW *= newStar.scaleX
        starH *= newStar.scaleY

        newStar.translationX = Math.random().toFloat() *
                containerW - starW / 2

        val mover = ObjectAnimator.ofFloat(
            newStar, View.TRANSLATION_Y,
            -starH, containerH + starH
        )
        mover.interpolator = AccelerateInterpolator(1f)
        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()

        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        set.start()
    }
}