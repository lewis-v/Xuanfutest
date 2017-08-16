package com.yw.xuanfutest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class MyServicePresenter {
    private final static int[] TYPE= { Sensor.TYPE_ACCELEROMETER,Sensor.TYPE_ORIENTATION,
            Sensor.TYPE_GYROSCOPE,Sensor.TYPE_MAGNETIC_FIELD,Sensor.TYPE_GRAVITY,
            Sensor.TYPE_LINEAR_ACCELERATION,Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_LIGHT,Sensor.TYPE_PRESSURE};

    private MyHomeAdapter myHomeAdapter;
    private Context context;
    private List<String> sensorInfo = new ArrayList<>();
    private List<Integer> sensorAll = new ArrayList<>();
    private IMyServicePersenter iMyServicePersenter;
    private SensorManager mSensorManager;
    private List<Sensor> sensorList = new ArrayList<>();
    private MySensorListener sensorListener;

    public MyServicePresenter(Context context, IMyServicePersenter iMyServicePersenter){
        this.iMyServicePersenter = iMyServicePersenter;
        this.context = context;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        for (int type :TYPE){
            Sensor sensor = mSensorManager.getDefaultSensor(type);
            if (sensor != null) {
                sensorList.add(sensor);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(type + "\n");
                stringBuilder.append("Sensor Name-" + sensor.getName() + "\n");
                sensorAll.add(sensor.getType());
                sensorInfo.add(stringBuilder.toString());
            }
        }
        myHomeAdapter = new MyHomeAdapter(context,sensorInfo);
        iMyServicePersenter.setRecyclerViewAdapter(myHomeAdapter);
    }

    /**
     * 设置传感器监听器
     */
    public void setSensorListener(){
        sensorListener = new MySensorListener();
        for (Sensor sensor:sensorList) {
            mSensorManager.registerListener(sensorListener
                    ,mSensorManager.getDefaultSensor(sensor.getType())
                    , SensorManager.SENSOR_DELAY_UI);
        }
    }

    /**
     * 传感器状态监听器
     */
    class MySensorListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            int type = event.sensor.getType();
            StringBuilder stringBuilder= new StringBuilder();
            switch (type){
                case Sensor.TYPE_ACCELEROMETER://加速度传感器
                    stringBuilder.append("加速度传感器返回数据：");
                    stringBuilder.append("\nX方向的加速度：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\nY方向的加速度：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\nZ方向的加速度：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_ORIENTATION://方向传感器
                    stringBuilder.append("方向传感器返回数据：");
                    stringBuilder.append("\n绕Z轴转过的角度：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\n绕X轴转过的角度：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\n绕Y轴转过的角度：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_GYROSCOPE://陀螺仪传感器
                    stringBuilder.append("陀螺仪传感器返回数据：");
                    stringBuilder.append("\n绕X轴旋转的角速度：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\n绕Y轴旋转的角速度：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\n绕Z轴旋转的角速度：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD://磁场传感器
                    stringBuilder.append("磁场传感器返回数据：");
                    stringBuilder.append("\nX轴方向上的磁场强度：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\nY轴方向上的磁场强度：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\nZ轴方向上的磁场强度：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_GRAVITY://重力传感器
                    stringBuilder.append("重力传感器返回数据：");
                    stringBuilder.append("\nX轴方向上的重力：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\nY轴方向上的重力：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\nZ轴方向上的重力：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION://线性加速度传感器
                    stringBuilder.append("线性加速度传感器返回数据：");
                    stringBuilder.append("\nX轴方向上的线性加速度：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    stringBuilder.append("\nY轴方向上的线性加速度：");
                    stringBuilder.append((float)((int)(values[1]*100))/100);
                    stringBuilder.append("\nZ轴方向上的线性加速度：");
                    stringBuilder.append((float)((int)(values[2]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE://温度传感器
                    stringBuilder.append("温度传感器返回数据：");
                    stringBuilder.append("\n当前温度为：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_LIGHT://光线传感器
                    stringBuilder.append("光传感器返回数据：");
                    stringBuilder.append("\n当前光的强度为：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
                case Sensor.TYPE_PRESSURE://压力传感器
                    stringBuilder.append("压力传感器返回数据：");
                    stringBuilder.append("\n当前压力为：");
                    stringBuilder.append((float)((int)(values[0]*100))/100);
                    sensorInfo.remove(sensorAll.indexOf(type));
                    sensorInfo.add(sensorAll.indexOf(type),stringBuilder.toString());
                    break;
            }
            myHomeAdapter.notifyDataSetChanged();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    /**
     * 停止处理
     */
    public void stop(){
        mSensorManager.unregisterListener(sensorListener);
    }
}
