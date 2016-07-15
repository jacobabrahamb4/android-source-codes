package com.example.c_bpras.aidlmdemoone;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.renderscript.Double2;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private IAdditionService mService = null;
    private boolean bound = false;
    private EditText number1;
    private EditText number2;
    private Button addButton;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AdditionService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        number1 = (EditText)findViewById(R.id.editText);
        number2 = (EditText)findViewById(R.id.editText2);
        addButton = (Button)findViewById(R.id.button);
        addButton.setOnClickListener(this);
        result = (TextView)findViewById(R.id.textView);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IAdditionService.Stub.asInterface(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            try {
                int num1 = Integer.parseInt(number1.getText().toString());
                int num2 = Integer.parseInt(number2.getText().toString());
                int result1 = mService.add(num1, num2);
                result.setText(Integer.toString(result1));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
