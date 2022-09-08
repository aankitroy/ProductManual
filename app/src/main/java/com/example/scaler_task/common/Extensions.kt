package com.example.scaler_task.common

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun Context.dimen(@DimenRes dimen: Int) = resources.getDimension(dimen).toInt()

fun Context.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Context.drawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun View.layoutInflater(): LayoutInflater = LayoutInflater.from(context)

fun Context.toPx(dp: Int): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)

fun Fragment.color(@ColorRes color: Int) = requireContext().color(color)

fun Fragment.drawable(@DrawableRes drawable: Int) = requireContext().drawable(drawable)

fun Fragment.toPx(dp: Int): Float = requireContext().toPx(dp)