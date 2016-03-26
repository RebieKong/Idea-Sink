package com.rebiekong.fire.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Response;
import com.rebiekong.fire.R;
import com.rebiekong.fire.base.BaseActivity;
import com.rebiekong.fire.data.ActivityCode;
import com.rebiekong.fire.data.StatusCode;
import com.rebiekong.fire.data.Urls;
import com.rebiekong.fire.util.data.json.Base;

import java.util.HashMap;
import java.util.Map;

public class AddIdea extends BaseActivity {

    public TextView textViewTitle;
    public TextView textViewContent;
    public TextView textViewAuthor;
    public EditText editTextAuthor;
    public EditText editTextTitle;
    public EditText editTextContent;
    public Button buttonAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        setContentView(R.layout.add_idea);
        textViewAuthor = (TextView) findViewById(R.id.add_author);
        editTextAuthor = (EditText) findViewById(R.id.add_author_edit);
        textViewTitle = (TextView) findViewById(R.id.add_title);
        editTextTitle = (EditText) findViewById(R.id.add_title_edit);
        textViewContent = (TextView) findViewById(R.id.add_content);
        editTextContent = (EditText) findViewById(R.id.add_content_edit);
        buttonAdd = (Button) findViewById(R.id.add_button_add);
        String author = getCacheString("author");
        editTextAuthor.setText(author);
        if (author.length() != 0) {
            editTextTitle.requestFocus();
        }
    }

    public void addIdea(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("title", editTextTitle.getText().toString());
        map.put("content", editTextContent.getText().toString());
        map.put("author", editTextAuthor.getText().toString());
        saveCacheString("author", editTextAuthor.getText().toString());
        buttonAdd.setEnabled(false);
        sendPost(Urls.getADD_IDEA(), map, new AddIdeaListener());
    }

    public class AddIdeaListener implements Response.Listener<com.rebiekong.fire.util.http.Response> {
        @Override
        public void onResponse(com.rebiekong.fire.util.http.Response response) {
            Base jsonObj = new Base(response.getData());
            toast(jsonObj.getString("msg"));
            if (jsonObj.getString("status").equals(StatusCode.SUCCESS)) {
                setResult(ActivityCode.REFLASH_CODE);
                finish();
            }
        }
    }

}


