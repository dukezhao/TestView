package com.example.z.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;


/**
 * Author: Z.King.James
 * Declarations: 金融投资使用的，投资产品的值的标尺，
 * Created on: 2018/12/17:14:39
 * Mail:mrzhaoxiaolin@163.com
 */
public class RulerView extends View {
    private Context mContext;
    private Paint centerLinePaint;  //中间刻度值画笔
    private Paint grayLinePaint;    //主画笔
    private Paint txtPaint;         //文字画笔
    private Paint currentTextPaint; //选中刻度画笔

    private int space = 60;//实际每一刻度之间间隔大小
    private int startValue = 1900;
    private int endValue = 2018;
    private int width;
    private int height;
    private float mLastX;
    private int touchSlop;
    private Scroller mScroller;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int maxScrollX = 1000; // 最大允许滑出范围
    private int currentOffset; // 当前偏移
    private VelocityTracker mVelocityTracker;
    private boolean isFastScroll;
    private AllRulerCallback mListener;
    private int number;
    private int BASELINE_OFFSET;
    private int interval = 5;
    private int textOffset = 50;
    private int mCountScale;

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

  /*  @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }*/

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        centerLinePaint = new Paint();
        centerLinePaint.setAntiAlias(true);
        centerLinePaint.setColor(context.getResources().getColor(R.color.yellow));//选中点，
        centerLinePaint.setStrokeWidth(5);

        grayLinePaint = new Paint();
        grayLinePaint.setAntiAlias(true);
        grayLinePaint.setColor(context.getResources().getColor(R.color.age_text));
        grayLinePaint.setStrokeWidth(5);

        txtPaint = new Paint();
        txtPaint.setAntiAlias(true);
        txtPaint.setColor(context.getResources().getColor(R.color.age_text));
        txtPaint.setTextSize(50);

        currentTextPaint = new Paint();
        currentTextPaint.setAntiAlias(true);
        currentTextPaint.setColor(context.getResources().getColor(R.color.white));
        currentTextPaint.setTextSize(50);
        currentTextPaint.setStrokeWidth(5);

        // 新增部分 start
        ViewConfiguration viewConfiguration = ViewConfiguration.get(mContext);
        touchSlop = viewConfiguration.getScaledTouchSlop();
        mScroller = new Scroller(mContext);
        mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        // 新增部分 end
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == View.MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else {
            width = mContext.getResources().getDisplayMetrics().widthPixels;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = (int) (mContext.getResources().getDisplayMetrics().density * 300 + 0.5);// 如果静态xml引用view, 且用wrap_content , will hit here
        }

        Log.i("test rulerview", "height:" + height);
        setMeasuredDimension(width, height);

        BASELINE_OFFSET = width / 2;
        int x = (number - startValue) * space - BASELINE_OFFSET + BASELINE_OFFSET % space;
        if (x % space != 0) {
            x -= x % space;
        }
        scrollTo(x, 0);
        computeAndCallback(x);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = startValue; i < endValue + 1; i++) {
            int lineHeight = 100;
            if (i % interval == 0) {
                lineHeight = 130;
                int x = (i - startValue) * space;
                if (x > 0 || x < width) {
                    canvas.drawText(String.valueOf(i) + "万", x - textOffset, lineHeight + 50, txtPaint);
                }
            }

            int startX = (i - startValue) * space;
            //add stopx 去加一条横线在标尺上方，的x的终点坐标，
            int stopX = (i + 1 - startValue) * space;
            //对最后一段横线省略，即画点到点，就是省略
            if (i == endValue) {
                stopX = startX;
            }

            if (startX > 0 || startX < width) {
                canvas.drawLine(startX, 50, startX, lineHeight, grayLinePaint);//startY :from 2->50, 让黄色三角看起来上移动，画竖线，
                canvas.drawLine(startX, 50, stopX, 50, grayLinePaint);//added 画横线
            }
        }


        int startX = BASELINE_OFFSET + getScrollX() - BASELINE_OFFSET % space;
        centerLinePaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(startX - 25, -5);
        path.lineTo(startX + 25, -5);
        path.lineTo(startX, 25);
        path.close();
        canvas.drawPath(path, centerLinePaint);
        // 空心
        centerLinePaint.setStyle(Paint.Style.STROKE);

        /*//每一屏幕刻度的个数/2
        int countScale = BASELINE_OFFSET/space;
        //根据滑动的距离，计算指针的位置【指针始终位于屏幕中间】
        int finalX = mScroller.getFinalX();
        //滑动的刻度
        int tmpCountScale = (int) Math.rint((double) finalX / (double) space); //四舍五入取整
        //总刻度
        mCountScale = tmpCountScale + countScale + startValue;
        //当处于某个整数，就绘制白色的发光文字和刻度,mCountScale是显示的即时数字
        int x = (mCountScale - startValue ) * space;
        if (mCountScale % interval == 0){
            canvas.drawText(String.valueOf(mCountScale), x-textOffset, 130, currentTextPaint);
            canvas.drawLine(startX, 10, startX, 80, currentTextPaint);
        }*/
        //canvas.drawLine(startX, 0, startX, 180, centerLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        obtainVelocityTracker();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isFastScroll = false;
                float moveX = event.getX();
                currentOffset = (int) (moveX - mLastX);
                scrollTo(getScrollX() - currentOffset, 0);
                computeAndCallback(getScrollX());
                mLastX = moveX;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) mVelocityTracker.getXVelocity();
                if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                    isFastScroll = true;
                    flingX(-initialVelocity);
                } else {
                    int x = getScrollX();
                    if (x % space != 0) {
                        x -= x % space;
                    }
                    if (x < -BASELINE_OFFSET) {
                        x = -BASELINE_OFFSET + BASELINE_OFFSET % space;
                    } else if (x > (endValue - startValue) * space - BASELINE_OFFSET) {
                        x = (endValue - startValue) * space - BASELINE_OFFSET + BASELINE_OFFSET % space;
                    }
                    scrollTo(x, 0);
                    computeAndCallback(x);
                }
                releaseVelocityTracker();
                break;
        }
        if (mVelocityTracker != null) {
            mVelocityTracker.addMovement(event);
        }
        return true;
    }

    /**
     * 惯性滑动
     *
     * @param velocityX
     */
    public void flingX(int velocityX) {
        mScroller.fling(getScrollX(), getScrollY(), velocityX, 0, -BASELINE_OFFSET, (endValue - startValue) * space - BASELINE_OFFSET, 0, 0);
        awakenScrollBars(mScroller.getDuration());
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int x = mScroller.getCurrX();
            scrollTo(x, 0);
            computeAndCallback(x);
            postInvalidate();
        } else {
            if (isFastScroll) {
                int x = mScroller.getCurrX() + BASELINE_OFFSET % space;
                if (x % space != 0) {
                    x -= x % space;
                }
                scrollTo(x, 0);
                computeAndCallback(x);
                postInvalidate();
            }
        }
    }

    /**
     * 计算并回调位置信息
     *
     * @param scrollX
     */
    private void computeAndCallback(int scrollX) {
        if (mListener != null) {
            int finalX = BASELINE_OFFSET + scrollX;
            if (finalX % space != 0) {
                finalX -= finalX % space;
            }
            mListener.onRulerSelected((endValue - startValue), startValue + finalX / space);
        }
    }

    /**
     * 初始化 速度追踪器
     */
    private void obtainVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    /**
     * 释放 速度追踪器
     */
    private void releaseVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public void setRuleListener(AllRulerCallback mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置第一次显示number的值
     *
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 设置刻度尺的最小值
     *
     * @param min
     */
    public void setMin(int min) {
        this.startValue = min;
    }

    /**
     * 设置刻度尺的最大值
     *
     * @param max
     */
    public void setMax(int max) {
        this.endValue = max;
    }

    /**
     * 设置刻度尺的数字显示间距值
     *
     * @param interval
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setTextOffset(int textOffset) {
        this.textOffset = textOffset;
    }
}
