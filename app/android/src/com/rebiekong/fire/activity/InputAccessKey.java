package com.rebiekong.fire.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.rebiekong.fire.R;
import com.rebiekong.fire.base.BaseActivity;
import com.rebiekong.fire.data.HandlerMsg;
import com.rebiekong.fire.data.Urls;
import com.rebiekong.fire.util.data.json.Base;
import com.rebiekong.fire.util.http.Response;

import java.util.HashMap;
import java.util.Map;

public class InputAccessKey extends BaseActivity {

    private TextView textViewAccessKey;
    private Button buttonAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        setContentView(R.layout.input_access_key);
        textViewAccessKey = (TextView) findViewById(R.id.access_key_edit);
        textViewAccessKey.setText(getCacheString("accessKey"));
        buttonAdd = (Button) findViewById(R.id.access_button_add);
        textViewAccessKey.requestFocus();
    }

    public void accessKey(View view) {
        String accessKey = textViewAccessKey.getText().toString();
        Map<String, String> map = new HashMap();
        map.put("access_key", accessKey);
        saveCacheString("accessKey", accessKey);
        sendPost(Urls.getCHECK_ACCESS(), map, new accessListener());
    }

    public class accessListener implements com.android.volley.Response.Listener<Response> {
        @Override
        public void onResponse(Response response) {
            String json = response.getData();
            Base jsonObj = new Base(json);
            toast(jsonObj.getString("msg"));
            String accessKey = jsonObj.getString("content_accesskey");
            saveCacheString("requestKey", accessKey);
            if (handler != null) {
                handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
            }
            finish();
        }
    }
}


