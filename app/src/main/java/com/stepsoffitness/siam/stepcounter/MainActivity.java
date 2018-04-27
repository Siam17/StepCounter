package com.stepsoffitness.siam.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count =(TextView) findViewById(R.id.cnt);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning =true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER); //TYPE_STEP_COUNTER is a counter that count steps since reboot untill unregister and next reboot
        if(countSensor !=null){
              //sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
              sensorManager.registerListener(this, countSensor ,sensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Count Sensor not available",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning) {
                count.setText(String.valueOf(event.values[0]));
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
