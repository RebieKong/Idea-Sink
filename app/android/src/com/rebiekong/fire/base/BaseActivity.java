package com.rebiekong.fire.base;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.rebiekong.fire.data.DeviceData;
import com.rebiekong.fire.data.HandlerMsg;
import com.rebiekong.fire.util.http.CustomRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 18029 on 2015/12/26.
 */
abstract public class BaseActivity extends Activity implements Response.Listener<com.rebiekong.fire.util.http.Response>, Response.ErrorListener {

    protected Handler handler;

    @Override
    public void onErrorResponse(VolleyError err) {
        try {
            int code = err.networkResponse.statusCode;
            if (code == 404) {
                toast("404 Not Found");
            } else if (code == 400) {
                toast("400 Bad Request");
            } else {
                toast("500 Internal Server Error");
            }
            if (handler != null) {
                handler.sendEmptyMessage(HandlerMsg.ENABLE_RELASH);
            }
        } catch (Exception e) {
            toast("PHONE ME 17092080427");
        }
    }

    @Override
    public void onResponse(com.rebiekong.fire.util.http.Response response) {
    }

    public boolean sendRequest(String url, Response.Listener listener) {
        return sendRequest(url, new HashMap(), Request.Method.POST, listener, this);
    }

    public boolean sendGet(String url, Map<String, String> params, Response.Listener listener) {
        String paramsString = "?";
        for (String key : params.keySet()) {
            paramsString += key + "=" + params.get(key).toString() + "&";
        }
        paramsString = paramsString.substring(0, paramsString.length() - 1);
        url += paramsString;
        return sendRequest(url, params, Request.Method.GET, listener, this);
    }

    public boolean sendPost(String url, Map<String, String> params, Response.Listener listener) {
        return sendRequest(url, params, Request.Method.POST, listener, this);
    }

    public void toast(String toast) {
        if (toast.length() > 0) {
            Toast.makeText(getApplicationContext(), toast,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public boolean sendRequest(String url, Map<String, String> params, int method, Response.Listener listener, Response.ErrorListener errorListener) {
        String key = getCacheString("requestKey");
        params.put("key", key);
        params.put("version", DeviceData.versionCode);
        params.put("UUID", DeviceData.UUID);
        params.put("mobile_number", DeviceData.mobileNumer);
        params.put("mobile_model", DeviceData.mobileModel);
        params.put("android_version", DeviceData.androidVersion);
        for (String paramKey:params.keySet()){
            Log.v("REQUEST_DATA",paramKey+"=>"+params.get(paramKey));
        }
        Request req = new CustomRequest(method, url, params, listener, errorListener);
        req.setRetryPolicy(new DefaultRetryPolicy(500000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(req);
        return true;
    }

    public boolean saveCacheString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    public String getCacheString(String key) {
        return getSharedPreferences("data", MODE_PRIVATE).getString(key, "");
    }

    protected void initApp() {
        DeviceData.setDeviceData(this);
    }
}
