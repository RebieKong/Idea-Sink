package com.rebiekong.fire.activity;

import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Response;
import com.rebiekong.fire.R;
import com.rebiekong.fire.base.BaseActivity;
import com.rebiekong.fire.data.ActivityCode;
import com.rebiekong.fire.data.HandlerMsg;
import com.rebiekong.fire.data.StatusCode;
import com.rebiekong.fire.data.Urls;
import com.rebiekong.fire.util.data.json.Base;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class StartUp extends BaseActivity {
    private TextView textViewTitel;
    private TextView textViewContent;
    private TextView textViewCommon;
    private TextView textViewComment;
    private Button buttonGet;
    private Button buttonDrop;
    private Button buttonAdd;
    private Button buttonRefuse;
    private LinearLayout linearLayoutComment;
    private String ideaId = "";
    private boolean timerMark = true;
    private boolean loadingData = false;
    private boolean loadNow = false;
    private Timer timer = null;
    final private Integer refreshTime = 1 * 60 * 1000;
    private Integer getCount = null;
    private Integer dropCount = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHandler();
        initApp();
        initView();
        initData();
    }

    public class StartUpActivityHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandlerMsg.DATA_REFLASH:
                    initData();
                    break;
                case HandlerMsg.ENABLE_RELASH:
                    if (buttonRefuse != null) {
                        buttonRefuse.setEnabled(true);
                    }
            }
            super.handleMessage(msg);
        }
    }

    private void setHandler() {
        handler = new StartUpActivityHandle();
    }

    private void initView() {
        setContentView(R.layout.main);
        textViewTitel = (TextView) findViewById(R.id.title);
        textViewContent = (TextView) findViewById(R.id.content);
        textViewCommon = (TextView) findViewById(R.id.common_title);
        textViewComment = (TextView) findViewById(R.id.comment);
        buttonGet = (Button) findViewById(R.id.get);
        buttonDrop = (Button) findViewById(R.id.drop);
        buttonAdd = (Button) findViewById(R.id.add);
        buttonRefuse = (Button) findViewById(R.id.main_reflash);
        linearLayoutComment = (LinearLayout) findViewById(R.id.comment_body);

        textViewCommon.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), InputAccessKey.class);
                startActivity(intent);
                return false;
            }
        });

        buttonGet.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (getCount != null) {
                    buttonGet.setText(buttonGet.getText().toString()+" (" + getCount.toString() + ")");
                }
                return true;
            }
        });

        buttonDrop.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (dropCount != null) {
                    buttonDrop.setText(buttonDrop.getText().toString()+" (" + dropCount.toString() + ")");
                }
                return true;
            }
        });
    }

    public void onPause() {
        super.onPause();
        timerMark = false;
    }

    public void onResume() {
        super.onResume();
        timerMark = true;
        if (loadNow) {
            loadNow = false;
            handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        Log.v("result","onresult:FROM="+String.valueOf(requestCode)+"|TO="+String.valueOf(resultCode));
        if (resultCode == ActivityCode.REFLASH_CODE ){
//            Log.v("result","on add comment");
            if (handler != null){
//                Log.v("result","on send message");
                handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
            }
        }
    }

    private void initData() {
        if (!loadingData) {
            buttonRefuse.setEnabled(false);
            buttonGet.setEnabled(false);
            buttonDrop.setEnabled(false);
            loadingData = true;
            sendRequest(Urls.getGET_IDEA(), new GetContent());
        }
    }

    public void setGetDrop(View view) {
        String data = "";
        switch (view.getId()) {
            case (R.id.get): {
                data = StatusCode.GET_IDEA;
                break;
            }
            case (R.id.drop): {
                data = StatusCode.DROP_IDEA;
                break;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("data", data);
        map.put("idea_id", ideaId);
        sendPost(Urls.getSET_GP(), map, new SetGpResponse());
    }

    public void addIdea(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AddIdea.class);
        startActivityForResult(intent, ActivityCode.MAIN_ACTIVITY);
    }

    public void addComment(View view) {
        Intent inte = new Intent();
        inte.setClass(this, AddComment.class);
        inte.putExtra("ideaId",Integer.parseInt(ideaId));
        startActivityForResult(inte, ActivityCode.MAIN_ACTIVITY);
    }

    public void userReflash(View view) {
        handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
    }

    private class refresh extends TimerTask {
        @Override
        public void run() {
//            Log.v("timer", "time to work!");
            if (timerMark) {
                if (!loadingData) {
//                    Log.v("timer", "let's go!");
                    handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
                }
            } else {
                loadNow = true;
            }
        }
    }

    public class SetGpResponse implements Response.Listener<com.rebiekong.fire.util.http.Response> {
        @Override
        public void onResponse(com.rebiekong.fire.util.http.Response response) {
            String json = response.getData();
            //Log.v("GPresponJSON", json);
            Base jsonObj = new Base(json);
            toast(jsonObj.getString("msg"));
            if (jsonObj.getString("status").equals(StatusCode.SUCCESS)) {
                buttonGet.setEnabled(false);
                buttonDrop.setEnabled(false);
            }
        }
    }

    public class GetContent implements Response.Listener<com.rebiekong.fire.util.http.Response> {
        @Override
        public void onResponse(com.rebiekong.fire.util.http.Response response) {
            String json = response.getData();
//            Log.v("Contentjson", json);
            Base jsonObj = new Base(json);
            if (jsonObj.getString("status").equals(StatusCode.SUCCESS)) {
                String newId = jsonObj.getString("content_id");
                if (ideaId.equals(newId)) {
                    //toast("304 Not Modified");
                    handler.sendEmptyMessage(HandlerMsg.DATA_REFLASH);
                    return;
                }
                //toast(jsonObj.getString("msg"));
                linearLayoutComment.removeAllViewsInLayout();
                textViewTitel.setText(jsonObj.getString("content_title"));
                textViewContent.setText(jsonObj.getString("content_content"));
                textViewComment.setText(jsonObj.getString("content_comment"));
                buttonGet.setText("捞");
                buttonDrop.setText("丢");

                String ding = jsonObj.getString("content_getCount");
                if (ding != null && ding.length() > 0){
                    getCount = Integer.parseInt(ding);
                }

                String diu = jsonObj.getString("content_dropCount");
                if (diu != null && diu.length() > 0){
                    dropCount = Integer.parseInt(diu);
                }

                ideaId = newId;
                try {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                } catch (Exception ignored) {
                } finally {
                    timer = new Timer();
                    timer.schedule(new refresh(), refreshTime);
                }
            }
            loadingData = false;
            buttonRefuse.setEnabled(true);
            buttonGet.setEnabled(true);
            buttonDrop.setEnabled(true);
        }
    }
}


