package com.ibareq.weathersample.ui
import android.view.View

object ViewVisibility {

    fun View.hide() {
        this.visibility = View.GONE
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

}