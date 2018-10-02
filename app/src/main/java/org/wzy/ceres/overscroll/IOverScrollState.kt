package org.wzy.ceres.overscroll

enum class IOverScrollState(value: Int) {
    /** No over-scroll is in-effect.  */
    STATE_IDLE(0),

    /** User is actively touch-dragging, thus enabling over-scroll at the view's *start* side.  */
    STATE_DRAG_START_SIDE(1),

    /** User is actively touch-dragging, thus enabling over-scroll at the view's *end* side.  */
    STATE_DRAG_END_SIDE(2),

    /** User has released their touch, thus throwing the view back into place via bounce-back animation.  */
    STATE_BOUNCE_BACK(3)
}