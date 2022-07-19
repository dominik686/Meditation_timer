package com.dominikwieczynski.meditationtimer

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AnimationUtils
import kotlin.math.hypot

object AnimationHelper {

    fun shakeAnimation(view : View)
    {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.shake)
        view.startAnimation(animation)
    }
     fun bounceAnimation(view : View)
    {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.bounce)
        view.startAnimation(animation)
    }

    fun circularReveal(view: View)
    {
        view.visibility = View.VISIBLE
        val cx = view.width / 2
        val cy = view.height / 2

        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
        // make the view visible and start the animation
        anim.start()
    }
}