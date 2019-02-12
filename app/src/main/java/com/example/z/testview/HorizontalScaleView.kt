package com.example.z.testview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.ContextMenu
import android.view.View

/**
 * Author: Z.King.James
 * Declarations://todo 一个kt的标尺类，
 * Created on: 2018/12/17:11:20
 * Mail:mrzhaoxiaolin@163.com
 */
class HorizontalScaleView(context: Context) : View(context) {
    lateinit var canvas: Canvas
    var paint = Paint()

    init {
        initPaint()
    }

    private fun initPaint() {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 10.0f
    }
}