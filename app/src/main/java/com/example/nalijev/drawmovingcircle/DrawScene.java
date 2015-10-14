package com.example.nalijev.drawmovingcircle;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DrawScene extends View {


    private Paint canvasPaint;
    private Paint circlePaint;

    public int x;
    public int y;
    private int circleRadius = 60;

    public int dx;
    public int dy;
    private final int FRAME_RATE = 50;

    private int scene_width;
    private int scene_height;

    private Handler h;

    public DrawScene(Context context, int start_x, int start_y, int start_dx, int start_dy) {
        super(context);
        x = start_x;
        y = start_y;
        dx = start_dx;
        dy = start_dy;

        canvasPaint = new Paint();
        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.BLACK);

        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);

        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override        public void run() {
            invalidate();
        }
    };

    @Override    protected void onDraw(Canvas sceneCanvas) {
        super.onDraw(sceneCanvas);

        sceneCanvas.drawPaint(canvasPaint);
        scene_width=this.getWidth();
        scene_height=this.getHeight();

        sceneCanvas.drawCircle(x, y, circleRadius, circlePaint);

        x += dx;
        y += dy;

        if ((x > scene_width - circleRadius) || (x < circleRadius)) {
            dx = dx*-1;
        }

        if ((y > scene_height - circleRadius) || (y < circleRadius)) {
            dy = dy*-1;
        }

        h.postDelayed(r, FRAME_RATE);
    }

    public Point pointXY() {
        Point p = new Point(this.x, this.y);
        return p;
    }

    public Point pointDxDy() {
        Point p = new Point(this.dx, this.dy);
        return p;
    }




}