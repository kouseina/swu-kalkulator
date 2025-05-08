package com.kouseina.kalkulator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GyroscopeActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroSensor;
    private TextView textViewGyro;
    private Button buttonStartGyro, buttonStopGyro;
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        textViewGyro = findViewById(R.id.textViewGyro);
        buttonStartGyro = findViewById(R.id.buttonStartGyro);
        buttonStopGyro = findViewById(R.id.buttonStopGyro);

        // Get SensorManager and Gyroscope sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Set listener for Gyroscope data
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    // Get the rotation values for the 3 axes
                    float x = event.values[0]; // Rotation around x-axis
                    float y = event.values[1]; // Rotation around y-axis
                    float z = event.values[2]; // Rotation around z-axis

                    // Update the TextView with the values
                    textViewGyro.setText("Gyroscope Data:\n" +
                            "X: " + x + "\n" +
                            "Y: " + y + "\n" +
                            "Z: " + z);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Handle sensor accuracy changes if necessary
            }
        };

        // Start and stop buttons
        buttonStartGyro.setOnClickListener(v -> startGyroscope());
        buttonStopGyro.setOnClickListener(v -> stopGyroscope());
    }

    private void startGyroscope() {
        if (gyroSensor != null) {
            // Register the listener to get sensor updates every 100 milliseconds
            sensorManager.registerListener(sensorEventListener, gyroSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private void stopGyroscope() {
        // Unregister the listener to stop receiving updates
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop listening when activity is paused
        stopGyroscope();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start listening when activity is resumed
        startGyroscope();
    }
}
