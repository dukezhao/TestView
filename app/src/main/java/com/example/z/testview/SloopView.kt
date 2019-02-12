package com.example.z.testview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * Author: Z.King.James
 * Declarations:customized view pracitise
 * Created on: 2018/11/7:22:12
 * Mail:mrzhaoxiaolin@163.com
 */
class SloopView(context: Context?) : View(context) {

    lateinit var canvas: Canvas
    var paint = Paint()//1创建画笔
    var paint2 = Paint()//创建画笔2

    /**
     * contractor 构造函数 ,kotlin中如何写这个构造函数?应该是不用， 用init{}即可
     * */
    /**  fun SloopView(context: Context)
    {


    initPaint()
    }*/

    init {
        initPaint()
    }

    /**
     *实际绘制内容
     */

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(200.0f, 199.0f, paint)
        canvas.drawPoints(floatArrayOf(100.0f, 100.0f, 300.0f, 200.0f, 200.0f, 130.0f), paint2)

        //draw line
        canvas.drawLine(200.0f, 200.0f, 500.0f, 500.0f, paint)

        canvas.drawLines(
            floatArrayOf(
                400.0f,
                400.0f,
                510.0f,
                510.0f,
                666.6f,
                789.34f,
                556.45f,
                745.64f
            ), paint2
        )


        //draw react

        /*       canvas.drawRect(100.0f, 100.0f, 800.0f, 400.0f, paint)

               //method 2
               var re = Rect(100, 100, 800, 400)//相当于在上边的rect外，描边
               canvas.drawRect(re, paint2)*/

        //method 3
        var rf = RectF(200.0f, 200.0f, 900.0f, 500.0f)
        paint.color = Color.GRAY
        canvas.drawRect(rf, paint)
        //draw round corner react

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(
                200.0f,
                200.0f,
                900.0f,
                500.0f,
                410.0f,//大于一半的宽度了
                310.0f,//大于一半的高度了
                paint2
            )//rx,ry 圆心 和 半径，其中圆心用于确定位置，而半径用于确定大小。，
            // 这里圆角矩形的角实际上不是一个正圆的圆弧，而是椭圆的圆弧，这里的两个参数实际上是椭圆的两个半径,
            //通过计算可知我们上次绘制的矩形宽度为700，高度为300，当你让 rx大于350(宽度的一半)， ry大于150(高度的一半) 时奇迹就出现了， 你会发现圆角矩形变成了一个椭圆
            //canvas.drawRectF(100.0f,100.0f,800.0f,400.0f,30.0f,30.0f,paint)
        }

        //椭圆 绘制 ，
        var round = RectF(300.0f, 300.0f, 1000.0f, 500.0f)
        canvas.drawOval(round, paint)


        //draw circle

        canvas.drawCircle(600.0f,600.0f,100.0f,paint2)

        //draw Arc 圆弧

    }

    /**
     *测量大小
     */

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //2.初始化画笔
    fun initPaint() {
        //test paint1
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 10.0f


        //test paint2
        paint2.color = Color.BLUE
        paint2.style = Paint.Style.FILL
        paint2.strokeWidth = 15.0f

    }

    /**
     * 确定view大小
     * */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }


    /**
     * 确定子View布局(自定义View包含子View时有用)
     * */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


}