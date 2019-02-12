package com.example.z.testview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import android.view.View

/**
 * Author: Z.King.James
 * Declarations:一个kt的自定义view 饼图
 * Created on: 2018/11/13:17:49
 * Mail:mrzhaoxiaolin@163.com
 */
class PieView(context: Context?) : View(context) {


    /*var mColors={0xFFCCFF00;0xFF6495ED; 0xFFE32636; 0xFF800000; 0xFF808000; 0xFFFF8C69; 0xFF808080;
        0xFFE6B800; 0xFF7CFC00}*/

    /**
     * todo
     * 初始化变量的语法待检验
     *
     */

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    //color table with alpha channel
    var mColors = intArrayOf(
        0xFFCCFF00.toInt(), 0xFF6495ED.toInt(),
        0xFFE32636.toInt(), 0xFF800000.toInt(),
        0xFF808000.toInt(), 0xFFFF8C69.toInt(),
        0xFF808080.toInt(), 0xFFE6B800.toInt(),
        0xFF7CFC00.toInt()
    )

 /*   var mColors = intArrayOf(
        444444,
        555555.toInt(),
        666666.toInt(),
        800000.toInt(),
        998000.toInt(),
        101010.toInt(),
        0x7f7f80.toInt(),
        0x194800.toInt(),
        0x830400.toInt()
    )*/

    // 饼状图初始绘制角度
    var mStartAngle = 0f
    // 数据
    lateinit var mData: ArrayList<PieData>
    var mWidth: Int = 0
    var mHeight: Int = 0
    var mPaint = Paint()


    init {

        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
       // mData = ArrayList()//必须初始化


        if (mData == null) {
            return
        }
        var currentStartAngle = mStartAngle// 当前起始角度
        canvas.translate(mWidth / 2f, mHeight / 2f) // 将画布坐标原点移动到中心位置
        var r = (Math.min(mWidth, mHeight) / 2f * 0.8).toFloat()// 饼状图半径
        var rect = RectF(-r, -r, r, r)// 饼状图绘制区域


        //循环 画弧度
        for (index: Int in 0 until mData.size) {
            var pie = mData[index]
            mPaint.color = pie.color

            Log.i("testcolor","the paint 's color is${mPaint.color}")
            canvas.drawArc(rect, currentStartAngle, pie.angle, true, mPaint)
            Log.i("testcolor","the currentangle 's$currentStartAngle")
            currentStartAngle += pie.angle//角度增加
            Log.i("testcolor","the next currentangle 's$currentStartAngle")
        }
    }

    // 设置起始角度
    fun setAtartAngle(startAngle: Float) {

        mStartAngle = startAngle

        invalidate()
    }

    // 设置数据
    /**
     * guide
     *   fun testStr(mStr: ArrayList<String>?) {

    if (mStr == null || mStr.size == 0) {
    return
    }
    }
     *
     * */


    fun setData(mData: ArrayList<PieData>) {
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新

    }

    /**
     * var pd1 = PieData("test1", 1.0f, 5f,0, 50f)//第二个value比较直接影响角度大小，后边percentage和angle不影响角度大小
    var pd2 = PieData("test2", 1.1f, 10f, 0, 70f)
    var pd3 = PieData("test3", 2.1f, 15f, 0, 100f)
    var pd4 = PieData("test4", 3.1f, 40f, 0, 150f)
    var pd5 = PieData("test5",3.1f, 30f, 0, 220f)

*/
    fun initData(mData: ArrayList<PieData>) {
        if (mData == null || mData.size == 0) {
            return
        }
        var sumValue = 0f
        for (index: Int in 0 until mData.size) {
            var pie = mData[index]

            //cal total value
            sumValue += pie.value

            //设置颜色
            var j: Int
            var tempI = index
            tempI %= mColors.size
            j = tempI
            //Log.i("testcolor", "j's value :$j")
            pie.color = mColors[j]
            //Log.i("testcolor","pie.color is:${pie.color}")
        }


        //设置百分比 对应角度等 ,总过角度应该360对不？？
        var sumAngle = 0f
        for (i: Int in 0 until mData.size) {
            var pie = mData[i]
            var percentage = pie.value
            Log.i("testcolor","percentage is :$percentage")
            var angle = percentage * 360//对应角度//todo 从360改为了30,改回360
            Log.i("testcolor","angle is :$angle")

            pie.percentage = percentage // 记录百分比
            Log.i("testcolor","pie.percentage is :${pie.percentage}")
            pie.angle = angle// 记录角度大小
            sumAngle += angle
        }

    }


}