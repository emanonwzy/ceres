package org.wzy.ceres.overscroll

import android.view.View

interface IOverScrollDecor {
    fun getView(): View
    fun setOverScrollStateListener(listener: IOverScrollStateListener)
    fun setOverScrollUpdateListener(listener: IOverScrollUpdateListener)
    fun getCurrentState(): Int
    fun detach()
}