package org.wzy.ceres.overscroll

import android.util.Property
import android.view.MotionEvent
import android.view.View

abstract class OverScrollBounceEffectDecoratorBase(val mViewAdapter: IOverScrollDecoratorAdapter,
                                                   val decelerateFactor: Float,
                                                   val touchDragRatioFwd: Float,
                                                   val touchDragRatioBck: Float) : View.OnTouchListener, IOverScrollDecor {

    companion object {
        val TAG: String = "OverScrollDecor"
        val DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD = 3.0
        val DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK = 1.0
        val DEFAULT_DECELERATE_FACTOR = -2.0
        val MAX_BOUNCE_BACK_DURATION_MS = 800
        val MIN_BOUNCE_BACK_DURATION_MS = 200
    }

    protected abstract class MotionAttributes {
        var mAbsOffset: Float = 0.0f
        var mDeltaOffset: Float = 0.0f
        var mDir: Boolean = false // True = 'forward', false = 'backwards'.
        abstract fun init(view: View, event: MotionEvent): Boolean
    }

    protected class OverScrollStartAttributes {
        var mPointerId: Int = 0
        var mAbsOffset: Float = 0.0f
        var mDir: Boolean = false // True = 'forward', false = 'backwards'.
    }

    protected abstract class AnimationAttributes {
        var mProperty: Property<View, Float>? = null
        var mAbsOffset: Float = 0.0f
        var mMaxOffset: Float = 0.0f
        protected abstract fun init(view: View)
    }

    interface IDecoratorState {
        fun handleMoveTouchEvent(event: MotionEvent): Boolean
        fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean
        fun handleEntryTransition(fromState: IDecoratorState)
        fun getStateId(): Int
    }

    protected var mVelocity: Float? = null
    protected val mStartAttr: OverScrollStartAttributes = OverScrollStartAttributes()
    protected val mIdleState: IdleState = IdleState()

    protected inner class IdleState: IDecoratorState {
        var mMoveAttr: MotionAttributes = createMotionAttributes()

        override fun handleMoveTouchEvent(event: MotionEvent): Boolean {
            val view = mViewAdapter.getView()
            if (!mMoveAttr.init(view, event)) {
                return false
            }

            if ((mViewAdapter.isInAbsoluteStart() && mMoveAttr.mDir)
                || (mViewAdapter.isInAbsoluteEnd() && !mMoveAttr.mDir)) {
                // Save initial over-scroll attributes for future reference.
                mStartAttr.mPointerId = event.getPointerId(0)
                mStartAttr.mAbsOffset = mMoveAttr.mAbsOffset
                mStartAttr.mDir = mMoveAttr.mDir
            }
            return false
        }

        override fun handleUpOrCancelTouchEvent(event: MotionEvent): Boolean {
            return false
        }

        override fun handleEntryTransition(fromState: IDecoratorState) {

        }

        override fun getStateId(): Int {
            return IOverScrollState.STATE_IDLE.ordinal
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

    }


    protected abstract fun createMotionAttributes(): MotionAttributes
    protected abstract fun createAnimationAttributes(): AnimationAttributes
    protected abstract fun translateView(view: View, offset: Float)
    protected abstract fun translateViewAndEvent(view: View, offset: Float, event: MotionEvent)
}