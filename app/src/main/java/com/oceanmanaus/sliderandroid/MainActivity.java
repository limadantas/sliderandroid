package com.oceanmanaus.sliderandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    PassadorMqtt passadorMqtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnLeft = (ImageView) findViewById(R.id.btn_left);
        ImageView btnRight = (ImageView) findViewById(R.id.btn_right);
        Button conectar = (Button) findViewById(R.id.conectar);

        final ConnFormHelper cfh = new ConnFormHelper(this);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passadorMqtt = new PassadorMqtt(MainActivity.this, cfh.getBroker(), cfh.getTopico());
                passadorMqtt.conectar();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passadorMqtt.enviar("esquerda");
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passadorMqtt.enviar("direita");
            }
        });
    }

    private class MqttTask extends AsyncTask<String, Integer, Void> {

        PassadorMqtt p;

        public MqttTask (PassadorMqtt passadorMqtt) {
            p = passadorMqtt;
        }

        protected Void doInBackground(String... args) {
            return null;
        }


        protected void onPostExecute(Void result) {

        }
    }

}
