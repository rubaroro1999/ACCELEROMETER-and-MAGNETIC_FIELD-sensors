package com.example.rubafikri.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor s;
    Sensor ss;

    ImageView im;
    float  rotation [] = new float[9];
    float  acc [] = new float[3];
    float  mag [] = new float[3];
    float  val [] = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = findViewById(R.id.im);



        sensorManager   = ( SensorManager ) getSystemService(this.SENSOR_SERVICE);
            s = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        ss = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
           if (s != null && ss != null){
               sensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(this,ss,SensorManager.SENSOR_DELAY_NORMAL);

           }else{
               Toast.makeText(this, "vv", Toast.LENGTH_SHORT).show();
           }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acc[0] = event.values[0];
            acc[1] = event.values[1];
            acc[2] = event.values[2];


        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mag[0] = event.values[0];
            mag[1] = event.values[1];
            mag[2] = event.values[2];
        }
        SensorManager.getRotationMatrix(rotation,null,acc,mag);
        SensorManager.getOrientation(rotation,val);
        im.setRotation(( float ) Math.toDegrees(val[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
