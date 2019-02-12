package com.example.z.testview

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.*
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.view.View
import android.view.WindowManager

/**
 * Author: Z.King.James
 * Declarations:测试用画布类
 * Created on: 2018/12/13:11:06
 * Mail:mrzhaoxiaolin@163.com
 */
class TestCanvas(context: Context?) : View(context) {
    var mScreenWid: Int = 0
    var mScreenHei: Int = 0
    lateinit var canvas: Canvas
    var paint = Paint()

    init {
        initPaint()
        getWindowsWidthandHeight()
    }

    private fun getWindowsWidthandHeight() {

        var wm: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mScreenWid = wm.defaultDisplay.width
        mScreenHei = wm.defaultDisplay.height


    }

    fun initPaint() {
        //test paint1
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 50.0f

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val mWidth = mScreenWid / 2.0f//get system haredware width & height of screen
        val mHeignt = mScreenHei / 2.0f


        //translate demo 1
        /*      canvas.translate(200.0f, 200.0f)
              canvas.drawCircle(0.0f, 0.0f, 50.0f, paint)//test ok ,

              //   //translate demo 2,在坐标原点绘制一个蓝色圆形
              paint.setColor(Color.BLUE);
              canvas.translate(200.0f, 200.0f);//将相对再次移动到前一次的坐标的+200x,+200y的target,而不是基于左上角0,0的移动坐标系，
              canvas.drawCircle(0.0f, 0.0f, 100.0f, paint);

              //  //translate demo 3,下列代码是移动到屏幕中心位置画圆，由于连续移动的原理，必须注释掉上述2个drawCircle才能真正移动到屏幕的中间

              paint.color=Color.YELLOW
              val mWidth = mScreenWid / 2.0f//get system haredware width & height of screen
              val mHeignt = mScreenHei / 2.0f
              Log.i("test", "screen wid:" + mWidth + ", height:" + mHeignt)
              canvas.translate(mWidth, mHeignt)//kotlin,移动到屏幕中心，
              canvas.drawCircle(0.0f,0.0f,108.0f,paint)*/

        //  //translate demo 4,于正中间画一矩形
        /*
               canvas.translate(mWidth, mHeignt)
               var rect: Rect = Rect(-200, -200, 200, 200)
               paint.color = Color.BLACK
               canvas.drawRect(rect, paint)
       */

        //translate demo 5，于中心处画点，之后发现高度没有取到中点
        /*
       canvas.translate(mWidth, mHeignt)
          paint.color = Color.YELLOW
          canvas.drawPoint(0.0f,0.0f,paint)//0.0f,0.0f表示位移，这里为0，即画布中心
          */

        //scale 缩放demo 1,缩放中心位于底部x轴所在的中部
      /*  canvas.translate(mWidth, mHeignt)
        var rect: RectF = RectF(0.0f, -400.0f, 400.0f, 0.0f)
        paint.color = Color.BLACK
        canvas.drawRect(rect, paint)
        //canvas.scale(0.5f, 0.5f)//-0.5f 标识反向翻转

        canvas.scale(0.5f,0.5f,200.0f,0.0f)//缩放& 位移
        paint.color = Color.BLUE
        canvas.drawRect(rect, paint)*/


        //scale 缩放demo 2，前面两个示例缩放的数值都是正数，按照表格中的说明，当缩放比例为负数的时候会根据缩放中心轴进行翻转

        //缩放中心一定要确定准，
        canvas.translate(mWidth, mHeignt)
        var rect: RectF = RectF(0.0f, -400.0f, 400.0f, 0.0f)//-400便宜后屏幕轴上方，
        paint.color = Color.BLACK
        canvas.drawRect(rect, paint)
        //canvas.scale(0.5f, 0.5f)//-0.5f 标识反向翻转

        canvas.scale(0.5f,0.5f,200.0f,0.0f)//缩放& 位移
        paint.color = Color.BLUE
        canvas.drawRect(rect, paint)



    }
}