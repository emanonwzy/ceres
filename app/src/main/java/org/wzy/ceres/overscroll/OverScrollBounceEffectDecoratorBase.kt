package org.wzy.ceres.overscroll

import android.view.MotionEvent
import android.view.View

public abstract class OverScrollBounceEffectDecoratorBase : View.OnTouchListener {

    companion object {
        val TAG: String = "OverScrollDecor"
        val DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3.0
        val DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1.0
        val DEFAULT_DECELERATE_FACTOR = -2.0
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

    }
}