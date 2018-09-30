package org.wzy.ceres.overscroll

interface IOverScrollUpdateListener {
    fun onOverScrollUpdate(decor: IOverScrollDecor, state: Int, offset: Float)
}