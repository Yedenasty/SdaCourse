package com.example.rent.sdacourse.Drawer;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by RENT on 2017-02-18.
 */

public class CustomPath {


    private int color;
    private Path path;

    public int getColor() {
        return color;
    }

    public CustomPath(int color, Point point) {
        this.color = color;
        this.path = new Path();
        this.path.moveTo(point.x, point.y);


    }

    public Path getPath() {
        return path;
    }
}
