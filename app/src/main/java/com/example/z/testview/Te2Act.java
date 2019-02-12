package com.example.z.testview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Author: Z.King.James
 * Declarations:
 * Created on: 2018/11/7:17:49
 * Mail:mrzhaoxiaolin@163.com
 */
public class Te2Act extends AppCompatActivity {
    TextView tv;
    private WindowManager windowManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv = findViewById(R.id.tv);暂时关闭，

        tstcode();
    }

    private void tstcode() {
        windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv.setText(event.getX() + "");
                return false;
            }
        });
    }
}
