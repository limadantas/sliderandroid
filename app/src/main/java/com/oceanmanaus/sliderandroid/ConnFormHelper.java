package com.oceanmanaus.sliderandroid;

import android.widget.EditText;

/**
 * Created by HE COMPUTADORES on 29/07/2016.
 */
public class ConnFormHelper {

    EditText broker;
    EditText topico;

    public ConnFormHelper (MainActivity ma) {
        broker = (EditText) ma.findViewById(R.id.broker);
        topico = (EditText) ma.findViewById(R.id.topico);
    }

    public void setDados (String broker, String topico) {
        this.broker.setText(broker);
        this.topico.setText(topico);
    }

    public String getBroker () { return broker.getText().toString(); }

    public String getTopico () { return topico.getText().toString(); }

}
