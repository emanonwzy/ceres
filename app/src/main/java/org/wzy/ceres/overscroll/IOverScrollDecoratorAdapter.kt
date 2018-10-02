package org.wzy.ceres.overscroll

import android.view.View

interface IOverScrollDecoratorAdapter {
    fun getView(): View
    fun isInAbsoluteStart(): Boolean
    fun isInAbsoluteEnd(): Boolean
}