package com.example.z.testview

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2018/11/13:17:49
 * Mail:mrzhaoxiaolin@163.com
 */
/*
class PieData {
    var name: String? = null
    var value: Float = 0f
    var percentage: Float = 0f
    var color = 0
    var angle = 0f

    init {
       */
/* name=name*//*


    }

}*/
/**
 *  kotlin数据类
 * */


/**
 *
 * 用户关心数据
private String name;        // 名字
private float value;        // 数值
private float percentage;   // 百分比

// 非用户关心数据
private int color = 0;      // 颜色
private float angle = 0;    // 角度
 *
 * */


data class PieData(
    var name: String,
    var value: Float,
    var percentage: Float,
    var color: Int = 0,
    var angle: Float = 10f
)
