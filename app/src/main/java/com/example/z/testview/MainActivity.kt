package com.example.z.testview

import android.graphics.Color
import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

/**
 *
 * 自定义view with kotlin
 * PieData 参数设置：value：设【0.1-1.0】之间数据，它会在Pieview里自动换算为角度。比如0.1*360=36度
 * Author ：Z.King.James
 * Date :2018-11-19
 * */


class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    lateinit var slpView: SloopView //自定义view
    lateinit var mPieView: PieView

    lateinit var testCan: TestCanvas//自定义view2, 测试画布

    lateinit var mPds: ArrayList<PieData>

    private lateinit var rulerView: RulerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //测试1 touch event事件 打印触点坐标，
        /**    setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)*/
        /**
         * var top=tv.top//该属性在measure和layout过程完成之后才开始赋值，而Measure一般都晚于onCreate方法执行，所以onCreateli get不到值
         *var left=tv.left
         *tv.text = "top 's top :$top,top's left :$left"////获取子View左上角距父View顶部的距离,why 0 //该属性在measure和layout过程完成之后才开始赋值，而Measure一般都晚于onCreate方法执行，所以onCreateli get不到值
         *
         */

        /**  tv.setOnTouchListener { v, event ->
        tv.text = "touched x:" + (event.x) + "\n,y:" + event.y + "\n rawX:" + event.rawX +
        "\n,rawY:" + event.rawY;
        false//kotlin 只需要返回最后一行

        }*/

        //测试2 test customized view ,
        /**
         * 1.自定义,见sloopView类
         *
         *
         * 2.动态加入布局（非xml方式）：就是把sloopView加入到releativelayout里，
         *    1.releativelayout
         *    2.setContent这个view ,以上2步应该可以从xml里获取*/

        /** var rl = RelativeLayout(this)//容器 //todo 初始化不能写在onCreate外，
        setContentView(rl)

        //test slpView , 第一阶段测试基本绘图，slpview 已ok
        slpView = SloopView(this)//子view
        var lp1: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
        )//参数
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        rl.addView(slpView, lp1)*/


        //测试3 ：自定义饼图

        //1初始化界面，下列将不能显示PieView
        /**
         * 以下代码不使用
         * var rl = RelativeLayout(this)//容器
        mPieView = PieView(this)//自定义图Pieview
        var lp1: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
        )//参数
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        rl.addView(mPieView, lp1)
        setContentView(rl)*/


        /***
         * the format of PieData 参数说明：
         *
         * data class PieData(var name:String,var value: Float,var percentage: Float , var color :Int= 0, var angle :Float= 0f )
         *   // 用户关心数据
        private String name;        // 名字
        private float value;        // 数值
        private float percentage;   // 百分比

        // 非用户关心数据
        private int color = 0;      // 颜色
        private float angle = 0;    // 角度
         *
         *
         * */

        /**
         * 使用说明：
         * @param value :设【0.1-1.0】之间数据，它会在Pieview里自动换算为角度。比如0.1*360=36度
         *
         * */


        /*  以下代码可以正常显示饼图，
          mPieView = PieView(this)

          mPds = ArrayList()
          var pd1 = PieData("test1", 0.1f, 0f, 0, 0f)//第二个value比较直接影响角度大小，后边percentage和angle不影响角度大小
          var pd2 = PieData("test2", 0.2f, 0f, 0, 0f)
          var pd3 = PieData("test3", 0.3f, 0f, 0, 0f)
          var pd4 = PieData("test4", 0.2f, 0f, 0, 0f)
          var pd5 = PieData("test5", 0.1f, 00f, 0, 0f)
          mPds.add(pd1)
          mPds.add(pd2)
          mPds.add(pd3)
          mPds.add(pd4)
          mPds.add(pd5)
          mPieView.setData(mPds)

          setContentView(mPieView)*/

        //测试4, 画布操作

        /**
         * 1.自定义,见TestCanvas类
         *
         *
         * 2.动态加入布局（非xml方式）：就是把sloopView加入到releativelayout里，
         *    1.releativelayout
         *    2.setContent这个view ,以上2步应该可以从xml里获取*/

        /** var rl = RelativeLayout(this)//容器 //todo 初始化不能写在onCreate外，
        setContentView(rl)

        //test slpView , 第一阶段测试基本绘图，slpview 已ok
        slpView = SloopView(this)//子view
        var lp1: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
        )//参数
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        rl.addView(slpView, lp1)*/


        //1.create a container
        /*   val mycontainer = RelativeLayout(this)
           setContentView(mycontainer)

           //2,TestCanvas
           testCan=TestCanvas(this)
           var lp1:RelativeLayout.LayoutParams =RelativeLayout.LayoutParams(
                   ViewGroup.LayoutParams.WRAP_CONTENT,
                   ViewGroup.LayoutParams.WRAP_CONTENT)
           lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
           lp1.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE)
           mycontainer.addView(testCan,lp1)
   */

        //test 5 自定义标尺, 测试 ok,左右滑动Ok，居中部分的值将system.out输入 ,ok
       /* val mycontainer = RelativeLayout(this)
        setContentView(mycontainer)

        //2,RulerView， 动态加入页面
        //初始化
        rulerView = RulerView(this)
        rulerView.setMin(1)
        rulerView.setMax(300)
        rulerView.setNumber(10)//first location that showing
        rulerView.setInterval(10)//间隔是10
        rulerView.setTextOffset(20)//根据显示的数字自主调节刻度尺数字的左右位置。 ,text,的偏移，
        var lp1: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)

        mycontainer.addView(rulerView, lp1)

        rulerView.setRuleListener(AllRulerCallback() { i: Int, i1: Int ->
            var number: String = i1.toString()
            System.out.println("select num: $number")
        })*/


        //3,RulerView， 静态加入页面，测试ok ，todo 适配后可以用于生产。
  /*      setContentView(R.layout.activity_main)
      *//*  val mycontainer = RelativeLayout(this)*//*
        rulerView = findViewById(R.id.rv_view)
        rulerView.setMin(0)
        rulerView.setMax(300)
        rulerView.setNumber(10)//first location that showing
        rulerView.setInterval(10)//间隔是10
        rulerView.setTextOffset(20)//根据显示的数字自主调节刻度尺数字的左右位置。 ,text,的偏移，

        rulerView.setRuleListener(AllRulerCallback() { i: Int, i1: Int ->
            var number: String = i1.toString()
            System.out.println("select num: $number")
        })*/


        //test 6 缩放scale,

        val mycontainer = RelativeLayout(this)
        setContentView(mycontainer)

        //2,TestCanvas
        testCan=TestCanvas(this)
        var lp1:RelativeLayout.LayoutParams =RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE)
        mycontainer.addView(testCan,lp1)

    }


    // 坐标系打印,响应手指触摸事件显示坐标时调用，测试3是下部应该先注释掉
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        /**  var top = tv.top//该属性在measure和layout过程完成之后才开始赋值，而Measure一般都晚于onCreate方法执行，所以onCreateli get不到值
        var left = tv.left
        var right = tv.right
        var bottom = tv.bottom
        tv.text = "tv 's top :$top,\n tv's left :$left,\ntv's right is $right,\ntv's down is$bottom" +
        "\n tv's width:${tv.width}" +
        "\ntv's height:${tv.height}"////获取子View左上角距父View顶部的距离,why 0 //todo 注意这里xml设置单位是px,
        //而如果设tp,则变为设置值*2.75的px*/

    }
}
