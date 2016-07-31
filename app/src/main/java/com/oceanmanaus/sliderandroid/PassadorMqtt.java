/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oceanmanaus.sliderandroid;

import android.content.Context;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author oceanmanaus
 */
public class PassadorMqtt  {

    String topic;
    int qos = 2;
    String broker;
    String clientId;
    Context context;
    MqttAndroidClient client;
    boolean conectado;

    MemoryPersistence memPer = new MemoryPersistence();

    public PassadorMqtt(final Context context, String broker, String topic) {
        this.topic = topic;
        this.broker = "tcp://"+broker;
        //this.qos = qos;
        this.clientId = MqttClient.generateClientId();
        this.context = context;

        client =
                new MqttAndroidClient(context.getApplicationContext(), broker,
                        clientId, memPer);

    }

    public void conectar() {
        try {
            /*IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Conectado com sucesso!", Toast.LENGTH_LONG).show();
                    conectado = true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "Conexão falhou!", Toast.LENGTH_LONG).show();
                    exception.printStackTrace();

                }
            });*/

            client.connect(null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Conectado com sucesso!", Toast.LENGTH_LONG).show();
                    conectado = true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(context, "Conexão falhou!", Toast.LENGTH_LONG).show();
                    exception.printStackTrace();

                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void enviar (String s) {

        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = s.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {

        try {
            IMqttToken disconToken = client.disconnect();

            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // we are now successfully disconnected
                    conectado = false;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // something went wrong, but probably we are disconnected anyway
                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
