package com.example.scaler_task.pojo

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.scaler_task.R

class Product {
    var name: String = ""
    var serialNumber = ""
    @DrawableRes
    var productImage: Int = 0
    @DrawableRes
    var serviceManImage: Int = 0
    var askMeAnythingText = ""
    @DrawableRes
    var screenBackground: Int = 0
    @DrawableRes
    var textBubbleBackground: Int = 0
    @ColorRes
    var askMeAnythingTextColor: Int = 0

    companion object {
        val PhilipsAirPurifier: Product = Product().apply {
            name = "Air Purifier"
            serialNumber = "C5659/20"
            productImage = R.drawable.airpurifierwithoutbg
            serviceManImage = R.drawable.service_man
            askMeAnythingText =
                "Ask me anything about your PHILIPS product!\n\nJust press the mic icon and speak your question."
            screenBackground = R.drawable.background
            textBubbleBackground = R.drawable.text_bubble_grey
            askMeAnythingTextColor = R.color.black
        }

        val UshaMixerGrinder: Product = Product().apply {
            name = "Mixer Grinder"
            serialNumber = "C5659/20"
            productImage = R.drawable.usha_mixer
            serviceManImage = R.drawable.ushahumanlogo
            askMeAnythingText =
                "Ask me anything about your USHA product!\n\nJust press the mic icon and speak your question."
            screenBackground = R.drawable.usha_background
            textBubbleBackground = R.drawable.text_bubble_dark_brown
            askMeAnythingTextColor = R.color.white
        }
    }

}