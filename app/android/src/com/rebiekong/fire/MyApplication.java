package com.rebiekong.fire;

import android.app.Application;
import com.rebiekong.fire.data.DeviceData;

/**
 * Created by rebie on 16-3-30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DeviceData.setDeviceData(this.getBaseContext());
    }
}
