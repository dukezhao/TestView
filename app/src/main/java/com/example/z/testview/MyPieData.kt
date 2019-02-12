package com.example.z.testview

import java.util.ArrayList

/**
 * Author: Z.King.James
 * Declarations:只是用来转化kotlin代码做比较比较的，
 * Created on: 2018/11/13:17:50
 * Mail:mrzhaoxiaolin@163.com
 */
class MyPieData {

    private val mColors = intArrayOf(
        -0x330100,
        -0x9b6a13,
        -0x1cd9ca,
        -0x800000,
        -0x7f8000,
        -0x7397,
        -0x7f7f80,
        -0x194800,
        -0x830400
    )

    // 用户关心数据
    var name: String? = null        // 名字
    var value: Float = 0.toFloat()        // 数值
    var percentage: Float = 0.toFloat()   // 百分比

    // 非用户关心数据
    var color = 0      // 颜色
    var angle = 0f    // 角度


    //todo test for from java to kotlin


    //todo

    //记住明天
    internal var mStr = ArrayList<String>()

    fun testStr(mStr: ArrayList<String>?) {

        if (mStr == null || mStr.size == 0) {
            return
        }
    }

}
