package com.m.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoActivity extends AppCompatActivity {
    private EditText etFirst, etSecond;
    private Button btnSum, btnSubtract;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_no);

        setTitle("Add two numbers using sensor");
        etFirst = findViewById(R.id.etFirst);
        etSecond = findViewById(R.id.etSecond);
        btnSum = findViewById(R.id.btnSum);
        btnSubtract = findViewById(R.id.btnSubtract);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
            }
        });
//        proximity();
        gyroscope();
    }

    private void subtract() {
        int subtract = Integer.parseInt(etFirst.getText().toString()) - Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Sum is " + subtract, Toast.LENGTH_SHORT).show();
    }


    private void add() {
        int add = Integer.parseInt(etFirst.getText().toString()) + Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Sum is " + add, Toast.LENGTH_SHORT).show();
    }


    private void gyroscope() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0) {
                    add();
                } else if (event.values[1] > 0) {
                    subtract();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
//    private void add() {
//        int sum = Integer.parseInt(etFirst.getText().toString() + Integer.parseInt(etSecond.getText().toString()));
//        Toast.makeText(this, "Sum is " + sum, Toast.LENGTH_SHORT).show();
//    }
//
//    private void proximity() {
//        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
//
//        SensorEventListener proxiListener = new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                if (event.values[0] <= 4){
//                    add();
//                }
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//            }
//        };
//
//    }

