package com.example.z.testview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * Author: Z.King.James
 * Declarations:
 * todo 包裹RulerViw希望在各屏幕间适配，但是没什么效果。
 * 注意构造函数中的this。自定义ViewGroup也能够有自己的属性，对于属性的操作和自定义View一致。
 * （在Style中添加自定义属性，在构造函数中获取到layout中设置的自定义属性的值
 *
 * Created on: 2018/12/18:09:49
 * Mail:mrzhaoxiaolin@163.com
 */
public class AutoRulerView extends FrameLayout {

    private final AutoLayoutHelper mHelper=new AutoLayoutHelper(this);

    public AutoRulerView(Context context) {
        super(context);
    }

    public AutoRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    /**
     * ensure the viewgroup's width,height, after this fun, the view's not been show
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //as vGroup is a container , when viewgroup has extractly value of width&height, the whole controller 's width&height is the value of itselve's,
        //consider the viewgroup Wrap_content mode, it's need to cal the whole controller's widh&height, and this value depend on cal the sub-view‘s layout

      int width;
      int height;
      int mWidthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
      measureChildren(widthMeasureSpec, heightMeasureSpec);
      if(mWidthMeasureMode==MeasureSpec.AT_MOST)//if wrap_content ,mesure the child 's size ,
      {
          //mesure the childview's width ,
          View childView=getChildAt(0);
          width=childView.getMeasuredWidth();

      }
      else
      {
          width=MeasureSpec.getSize(widthMeasureSpec);
      }
      int mHeightMesureMode=MeasureSpec.getMode(heightMeasureSpec);
      if(mHeightMesureMode==MeasureSpec.AT_MOST)
      {
          View childView=getChildAt(0);
          height=childView.getMeasuredHeight();
      }
      else
      {
          height=MeasureSpec.getSize(heightMeasureSpec);
      }

      setMeasuredDimension(width,height);


        //以下代码位置？ todo
        if(!isInEditMode())
        {
            mHelper.adjustChildren();
        }

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

    }

    /**
     * customized viewgroup's subview 's location & layout
     *
     * */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.e("autoRuleerview" ,"paddingLeft="+l+"；paddingTop="+t+";paddingRight="+r+";paddingBottom="+b);


        View subView=getChildAt(0);
        subView.layout(0,20,subView.getMeasuredWidth(),subView.getMeasuredHeight());
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }
    public static class LayoutParams extends FrameLayout.LayoutParams implements AutoLayoutHelper.AutoLayoutParams
    {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo=AutoLayoutHelper.getAutoLayoutInfo(c,attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }
        public LayoutParams(int width,int height){
            super(width,height);
        }
        public LayoutParams(ViewGroup.LayoutParams source){super(source);}
        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }
    }

}
