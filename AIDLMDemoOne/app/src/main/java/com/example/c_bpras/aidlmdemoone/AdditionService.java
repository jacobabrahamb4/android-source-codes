package com.example.c_bpras.aidlmdemoone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AdditionService extends Service {
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    IAdditionService.Stub mBinder = new IAdditionService.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }
    };

}
