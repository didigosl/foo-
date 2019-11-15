package com.technology.greenenjoyshoppingstreet.ktnewui.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ViewFlipper
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

fun ViewGroup.inflate(layoutId:Int): View {
    return LayoutInflater.from(context).inflate(layoutId,this,false)
}

fun ViewFlipper.addImage(url:String,cxt:Context): Unit{
    val image = ImageView(cxt)
    image.scaleType = ImageView.ScaleType.FIT_XY
    Glide
            .with(cxt)
            .load(url)
            .into(image)
    this.addView(image)
    this.setFlipInterval(4000)
    this.isAutoStart = true

    this.animation = AnimationUtils.loadAnimation(cxt,android.R.anim.slide_in_left)
    this.outAnimation = AnimationUtils.loadAnimation(cxt,android.R.anim.slide_out_right)
}

inline fun <reified T> Gson.fromJson(json: JsonElement) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)