package com.example.manuel.ejemplo_temperatura;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;



public class MainActivity extends AppCompatActivity {
    TextView textTemperatura,texTitulo,textSimbolo,texTemFa,texSimboloF;
    private StartAppAd startAppAd = new StartAppAd(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartAppSDK.init(this, "101423750", "203739616", true);
        textTemperatura = (TextView) findViewById(R.id.textTemp);
        texTitulo = (TextView) findViewById(R.id.textView_Titulo);
        textSimbolo = (TextView) findViewById(R.id.textView_Simbolo);
        texTemFa = (TextView) findViewById(R.id.textTempF);
        texSimboloF = (TextView) findViewById(R.id.textSimbolo2);



        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor TemperaturaAmbiente = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        if (TemperaturaAmbiente != null) {
            sensorManager.registerListener(TemperaturaAmbienteSensorListener,TemperaturaAmbiente,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else {

            batteryLevel();

        }
    }

    private final SensorEventListener TemperaturaAmbienteSensorListener
            = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_TEMPERATURE){

                final float [] values = event.values;
                final  double faren = values[0]*1.8+32;
                //convertimos float a String
                final String convertido;
                convertido = String.format("%.1f",values[0]);
                final String grafaren;
                grafaren = String.format("%.1f",faren);
                final String[] mostrar_ambiente = new String[1];
                // mostrar_ambiente[0]=convertido;

                //colores temperatura

                if(values[0] >10 || values[0]< 20){
                    textTemperatura.setTextColor(getResources().getColor(R.color.color_normal));
                    texTemFa.setTextColor(getResources().getColor(R.color.color_normal));
                }
                if (values[0]>= 20){
                    textTemperatura.setTextColor(getResources().getColor(R.color.colo_calor));
                    texTemFa.setTextColor(getResources().getColor(R.color.colo_calor));
                }
                if (values[0]<= 10){
                    textTemperatura.setTextColor(getResources().getColor(R.color.color_frio));
                    texTemFa.setTextColor(getResources().getColor(R.color.color_frio));
                }
                //colores texto temperatura
                if(values[0] >10 || values[0]< 20){
                    texTitulo.setTextColor(getResources().getColor(R.color.color_normal));
                    textSimbolo.setTextColor(getResources().getColor(R.color.color_normal));
                    texSimboloF.setTextColor(getResources().getColor(R.color.color_normal));
                }
                if (values[0]>= 20){
                    texTitulo.setTextColor(getResources().getColor(R.color.colo_calor));
                    textSimbolo.setTextColor(getResources().getColor(R.color.colo_calor));
                    texSimboloF.setTextColor(getResources().getColor(R.color.colo_calor));
                }
                if (values[0]<= 10){
                    texTitulo.setTextColor(getResources().getColor(R.color.color_frio));
                    textSimbolo.setTextColor(getResources().getColor(R.color.color_frio));
                    texSimboloF.setTextColor(getResources().getColor(R.color.color_frio));
                }



                textTemperatura.setText(convertido);
                textSimbolo.setText(R.string.grados_centigrados);
                texTemFa.setText(grafaren);
                texSimboloF.setText(R.string.grados_faren);



            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    //Mostrar temperatura bateria

    public void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                //temperatura bateria
                final int temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
                //redondeamos y quitamos 4 grados
                //final float temperatura = (temperature/10);
                float temperatura = (((float) temperature) / 10)-4;
                double faren = (((double)temperatura)*1.8) +32;
                //convertimos a String
                final String convertido;
                convertido = Float.toString(temperatura);
                final String grafaren;
                grafaren = String.format("%.1f",faren);
                // final String[] mostrar_bateria = new String[1];

                //colores temperatura

                if(temperatura >10 || temperatura< 20){
                    textTemperatura.setTextColor(getResources().getColor(R.color.color_normal));
                    texTemFa.setTextColor(getResources().getColor(R.color.color_normal));
                }
                if (temperatura>= 20){
                    textTemperatura.setTextColor(getResources().getColor(R.color.colo_calor));
                    texTemFa.setTextColor(getResources().getColor(R.color.colo_calor));
                }
                if (temperatura<= 10){
                    textTemperatura.setTextColor(getResources().getColor(R.color.color_frio));
                    texTemFa.setTextColor(getResources().getColor(R.color.color_frio));
                }
                //colores texto temperatura
                if(temperatura >10 || temperatura< 20){
                    texTitulo.setTextColor(getResources().getColor(R.color.color_normal));
                    textSimbolo.setTextColor(getResources().getColor(R.color.color_normal));
                    texSimboloF.setTextColor(getResources().getColor(R.color.color_normal));
                }
                if (temperatura>= 20){
                    texTitulo.setTextColor(getResources().getColor(R.color.colo_calor));
                    textSimbolo.setTextColor(getResources().getColor(R.color.colo_calor));
                    texSimboloF.setTextColor(getResources().getColor(R.color.colo_calor));
                }
                if (temperatura<= 10){
                    texTitulo.setTextColor(getResources().getColor(R.color.color_frio));
                    textSimbolo.setTextColor(getResources().getColor(R.color.color_frio));
                    texSimboloF.setTextColor(getResources().getColor(R.color.color_frio));
                }


                textTemperatura.setText(convertido);
                textSimbolo.setText(R.string.grados_centigrados);
                texTemFa.setText(grafaren);
                texSimboloF.setText(R.string.grados_faren);

            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){


            case R.id.salir:
                //botón salir

                MainActivity.this.startAppAd.showAd();
                MainActivity.this.startAppAd.loadAd();

                finish();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {

        super.onResume();
        startAppAd.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //poner en marcha la publicidad al salir
        MainActivity.this.startAppAd.showAd();
        MainActivity.this.startAppAd.loadAd();

    }

    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }

}
