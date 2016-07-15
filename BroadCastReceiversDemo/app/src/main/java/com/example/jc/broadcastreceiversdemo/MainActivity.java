package com.example.jc.broadcastreceiversdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    public static final String ACTION_BROADCAST = "sample.jac.action";
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button)findViewById(R.id.button);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button) {
            Intent intent = new Intent(ACTION_BROADCAST);
            //intent.setClass(this, ReceiverExplicit.class);
            sendBroadcast(intent);
        }
    }
}
