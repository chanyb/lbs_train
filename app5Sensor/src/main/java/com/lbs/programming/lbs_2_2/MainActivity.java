package com.lbs.programming.lbs_2_2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerationSensor;
    private Sensor rotationVectorSensor;
    private Sensor stepCounterSensor;

    private TextView textViewValueX;
    private TextView textViewValueY;
    private TextView textViewValueZ;

    private TextView textViewRotationValueX;
    private TextView textViewRotationValueY;
    private TextView textViewRotationValueZ;

    private TextView textViewStepCountValue;

    private long lastAccelerationEventTime = 0;
    private long lastRotationEventTime = 0;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    if (System.currentTimeMillis() - lastAccelerationEventTime > 100) {
                        lastAccelerationEventTime = System.currentTimeMillis();
                        // TODO: Sensor 값 표출
                        textViewValueX.setText(String.format("%3.2f", event.values[0]));
                        textViewValueY.setText(String.format("%3.2f", event.values[1]));
                        textViewValueZ.setText(String.format("%3.2f", event.values[2]));
                    }
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    if (System.currentTimeMillis() - lastRotationEventTime > 100) {
                        lastRotationEventTime = System.currentTimeMillis();

                        // TODO: Sensor 값 표출
                        textViewRotationValueX.setText(String.format("%3.2f", event.values[0]));
                        textViewRotationValueY.setText(String.format("%3.2f", event.values[1]));
                        textViewRotationValueZ.setText(String.format("%3.2f", event.values[2]));

                    }
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    // TODO: Sensor 값 표출
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: SensorManager 등록
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        showSensorList();

        textViewValueX = findViewById(R.id.textViewValueX);
        textViewValueY = findViewById(R.id.textViewValueY);
        textViewValueZ = findViewById(R.id.textViewValueZ);

        textViewRotationValueX = findViewById(R.id.textViewRotationValueX);
        textViewRotationValueY = findViewById(R.id.textViewRotationValueY);
        textViewRotationValueZ = findViewById(R.id.textViewRotationValueZ);

        textViewStepCountValue = findViewById(R.id.textViewStepCountValue);
    }

    private void showSensorList() {
        // TODO: Sensor 리스트 출력
        List<Sensor> sensorList =
                sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            Log.e("SensorList", sensor.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Sensor 등록
        accelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);

        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(sensorEventListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(sensorEventListener, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO: Sensor 등록 취소
    }
}
