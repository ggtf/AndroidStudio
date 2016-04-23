package com.ggtf.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) findViewById(R.id.txt);

        /**
         * 获取传感器的管理器
         */
        SensorManager manager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        /**
         * 获取所有传感器列表
         */
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        /**
         * 获取某一类的传感器列表
         */
        List<Sensor> sensorsGravity = manager.getSensorList(Sensor.TYPE_GRAVITY);
        /**
         * 获取当前某一类默认的传感器
         */
        final Sensor defaultSensorLight = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        final Sensor defaultSensorGyroscope = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        /**
         * 显示传感器列表
         * Sensor.TYPE_GRAVITY;重力传感器
         */
        for (int i = 0; i < sensors.size(); i++) {
            Log.i("Info","传感器名"+sensors.get(i).getName()+
                    "-----传感器所属类型"+sensors.get(i).getStringType()+
                    "-----传感器当前数值"+sensors.get(i).getPower());
        }
        /**
         * 传感器事件监听
         */
        SensorEventListener sensorEventListener = new SensorEventListener() {
            /**
             * 当数值发生变化时
             * @param event
             */
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor == defaultSensorLight){
                    float[] values = event.values;
                    for (int i = 0; i < values.length; i++) {
                        Log.i("Info","获取光的强度"+values[i]+"");
                    }
                }
//                if (event.sensor == defaultSensorGyroscope){
//                    Log.i("Info","Power = "+defaultSensorGyroscope.getPower());
//                    Log.i("Info","MaximumRange = "+defaultSensorGyroscope.getMaximumRange());
//                }
            }

            /**
             * 当精度发生变化时
             * @param sensor
             * @param accuracy
             */
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        /**
         * 注册监听
         */
        manager.registerListener(sensorEventListener,defaultSensorLight,SensorManager.SENSOR_DELAY_NORMAL);
//        manager.registerListener(sensorEventListener,defaultSensorGyroscope,SensorManager.SENSOR_DELAY_UI);

    }

}
