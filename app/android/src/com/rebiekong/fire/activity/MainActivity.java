package com.rebiekong.fire.activity;

import android.os.Bundle;
import com.rebiekong.fire.R;
import com.rebiekong.fire.base.BaseActivity;

/**
 * Created by rebie on 16-3-30.
 */
public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initData();
        initView();
    }

    private void initData(){
        sendPost();

    }
    private void initView(){}
}
