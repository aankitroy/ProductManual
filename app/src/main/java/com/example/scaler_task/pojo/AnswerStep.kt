package com.example.scaler_task.pojo

import androidx.annotation.DrawableRes

data class AnswerStep(
    val stepNumber: Int,
    val text: String,
    @DrawableRes val image: Int
) {
}