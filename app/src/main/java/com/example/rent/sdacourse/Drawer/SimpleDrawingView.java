package com.example.rent.sdacourse.Drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class SimpleDrawingView extends View {
    private Paint paint;
    private Path path = new Path();
    private List<CustomPath> paths = new ArrayList<>();
   @ColorInt
    private int currentlySelectedColor = Color.BLACK;
    private int wielkoscgumy = 10;

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(wielkoscgumy);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(CustomPath customPath : paths) {
            paint.setColor(customPath.getColor());
        canvas.drawPath(customPath.getPath(), paint);
    }}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                paint.setStrokeWidth(wielkoscgumy);
                paths.add(new CustomPath(currentlySelectedColor, new Point(x,y)));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                paths.get(paths.size()-1).getPath().lineTo(x,y);
                break;
            }
        }
        postInvalidate();
        return true;
    }
    public void setCurrentCollor (int color) {

        currentlySelectedColor = color;

    }

public void clear(){
    paths.clear();
    postInvalidate();
}


    public void setWielkoscgumy(int wielkoscgumy) {
        this.wielkoscgumy = wielkoscgumy;
    }
}
