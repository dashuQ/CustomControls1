package com.example.customcontrols.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义圆
 * Created by lenovo on 2017/4/25.
 */

public class MyAnimation extends View {

    private Paint p;
    private float degrees = 0;


    private void initProperties() {
        p = new Paint();
        p.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();//绘制前保存下
        canvas.rotate(degrees, 50, 50);//设置旋转角度
        canvas.drawRect(0, 0, 100, 100, p);//绘制
        degrees++;
        canvas.restore();
        invalidate();


    }

    public MyAnimation(Context context) {
        super(context);
        initProperties();
    }


    public MyAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public MyAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initProperties();
    }

}
