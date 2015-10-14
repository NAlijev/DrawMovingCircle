package com.example.nalijev.drawmovingcircle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

    private int start_x;
    private int start_y;
    private int start_dx;
    private int start_dy;

    private DrawScene drawScene;
    public static final String PREFS_NAME = "SharedPreferencesFile";

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        start_x = storedData.getInt("x", 50);
        start_y = storedData.getInt("y", 50);
        start_dx = storedData.getInt("dx", 50);
        start_dy = storedData.getInt("dy", 50);

        drawScene = new DrawScene(this, start_x, start_y, start_dx, start_dy);
        setContentView(drawScene);
    }


    @Override protected void onStop() {
        super.onStop();
        this.saveRates();
    }

    protected void saveRates() {
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("x", drawScene.pointXY().x);
        storedDataEditor.putInt("y", drawScene.pointXY().y);
        storedDataEditor.putInt("dx", drawScene.pointDxDy().x);
        storedDataEditor.putInt("dy", drawScene.pointDxDy().y);

        storedDataEditor.commit();

    }
}




