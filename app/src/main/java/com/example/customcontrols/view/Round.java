package com.example.customcontrols.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.customcontrols.R;

/**
 * 自定义圆
 * Created by lenovo on 2017/4/25.
 */

public class Round extends View {

    public Round(Context context) {
        super(context);
        init();
    }

    private RectF oval;
    private Paint paint1;
    private Paint paint2;//圆环
    private Paint paint3;//文字
    private float radius = 200;//半径
    private float w = 50;//宽
    private float w2 = 50 / 2;//宽
    private float x = 200 + w2;//画圆的中心点
    private float y = 200 + w2;//画圆的中心点
    private int min = 0;
    private int max = 100;

    /**
     * 画圆
     */
    private void init() {

        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setAntiAlias(true);//去锯齿
        paint1.setStyle(Paint.Style.STROKE);//画笔样式
        paint1.setStrokeWidth(w);//画笔宽度,圆环的宽度

        paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setAntiAlias(true);//去锯齿
        paint2.setStyle(Paint.Style.STROKE);//画笔样式
        paint2.setStrokeWidth(w);//画笔宽度,圆环的宽度
//        oval = new RectF(w2, w2, radius * 2 + w2, radius * 2 + w2);//不用在此初始了

        paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setAntiAlias(true);//去锯齿
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initrectf();//自已去确定位置画

        //当前角度
        float angli = (float) min / max * 360;

//        canvas.drawCircle(x, y, radius, paint1);//画圆
        canvas.drawCircle(width/2, heigth/2, radius, paint1);//画圆


//        canvas.drawArc(oval, -90, 180, false, paint1);//画圆弧
//        canvas.drawArc(oval, -90, angli, false, paint2);//画圆弧
        canvas.drawArc(oval, -90, angli, false, paint2);//画圆弧

        canvas.drawText(min + "%", width/2, heigth/2, paint3);

        if (min < max) {
            min++;
            invalidate();//强制调用onDraw方法
        }

    }

    private void initrectf() {
        if(oval==null){
            oval=new RectF();
            int viewSize=(int)(radius *2);
            int left =(width-viewSize)/2;
            int top =(heigth-viewSize)/2;
            int right =left+viewSize;
            int bottom =top+viewSize;
            oval.set(left,top,right,bottom);
        }
    }

    public Round(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //读取自定义属性
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.mydeclare);
        int color=ta.getColor(R.styleable.mydeclare_mycolor,0xffff0000);//declare-styleableName_attrName
        setBackgroundColor(color);

        init();
    }

    public Round(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Round(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //宽高
    private int width;
    private int heigth;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width= getSize(widthMeasureSpec);
        heigth= getSize(heightMeasureSpec);
        setMeasuredDimension(width,heigth);
    }

    private int getSize(int spec) {

        int result = -1;

        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);
        //测量
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {//自已测量宽高
            result = (int)(radius * 2 + w);
        } else {
            result=size;
        }

        return result;
    }
}
