package com.tech.sensortest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vibrator service

        var vibrator=applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        //1
        var sensorManager=applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        var sensorp=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

       //2
        var sensorName=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //4
        var listener= object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {

               var values= event?.values
                text1.setText("value 1   :${values?.get(0).toString()}")
                text2.setText("value 2   :${values?.get(1).toString()}")
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }
        var listenerp= object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {

                var values= event?.values
                text3.setText("value 1   :${values?.get(0).toString()}")
                vibrator.vibrate(1000);

            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

        }

        sensorManager.registerListener(listenerp,sensorp,SensorManager.SENSOR_DELAY_NORMAL);


        //3
        sensorManager.registerListener(listener,sensorName,SensorManager.SENSOR_DELAY_NORMAL);

        button.setOnClickListener {
            vibrator.vibrate(3000);
        }
    }
}