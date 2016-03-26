package com.rebiekong.fire.data;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.rebiekong.fire.util.MUID;

import java.io.Serializable;

/**
 * Created by Rebie on 2015/12/29.
 */
public class DeviceData implements Serializable {

    public static String UUID;
    public static String mobileNumer;
    public static String mobileModel;
    public static String androidVersion;
    public static String versionCode;

    public static void setDeviceData(Activity activity) {
        try {
            UUID = MUID.id(activity.getBaseContext());
            if (UUID == null) UUID = "";
        } catch (Exception e) {
            UUID = "";
        }
        try {
            mobileNumer = ((TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE)).getLine1Number();
            if (mobileNumer == null) mobileNumer = "";
        } catch (Exception e) {
            mobileNumer = "";
        }
        try {
            mobileModel = android.os.Build.MODEL;
            if (UUID == null) UUID = "";
        } catch (Exception e) {
            mobileModel = "";
        }
        try {
            androidVersion = android.os.Build.VERSION.RELEASE;
            if (androidVersion == null) androidVersion = "";
        } catch (Exception e) {
            androidVersion = "";
        }
        try {
            PackageManager packageManager = activity.getBaseContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.rebiekong.fire", 0);
            versionCode = packageInfo.versionName;
            if (versionCode == null) versionCode = "";
            if (TextUtils.isEmpty(versionCode)) {
                versionCode = "";
            }
        } catch (Exception e) {
            versionCode = "";
        }
    }
}
