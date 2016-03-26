package com.rebiekong.fire.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class AddComment extends BaseActivity {

    private int ideaId;
    private EditText editTextContent;
    private EditText editTextAuthor;
//    private RatingBar ratingBar;
    private Button buttonAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ideaId = getIntent().getIntExtra("ideaId",0);
        if (ideaId == 0){
            toast("400 Bad Request");
            finish();
        }
        initView();
    }

    public void initView() {
        setContentView(R.layout.add_comment);
        editTextContent = (EditText) findViewById(R.id.add_comment_content);
        editTextAuthor = (EditText) findViewById(R.id.add_author);
//        ratingBar = (RatingBar) findViewById(R.id.add_comment_rate);
        buttonAdd = (Button) findViewById(R.id.add_comment_button);

        String author = getCacheString("author");
        editTextAuthor.setText(author);
        if (author.length() != 0) {
            editTextContent.requestFocus();
        }
    }

    public void addComment(View view){
        Map map = new HashMap<String,String>();
        map.put("content",editTextContent.getText().toString());
        map.put("author", editTextAuthor.getText().toString());
        map.put("idea_id",String.valueOf(ideaId));
//        map.put("rate",String.valueOf(ratingBar.getRating()));
        saveCacheString("author", editTextAuthor.getText().toString());
        sendPost(Urls.getADD_COMMENT(),map,new AddCommentListener());
    }

    public class AddCommentListener implements Response.Listener<com.rebiekong.fire.util.http.Response> {
        @Override
        public void onResponse(com.rebiekong.fire.util.http.Response response) {
            Base jsonObj = new Base(response.getData());
            toast(jsonObj.getString("msg"));
            if (jsonObj.getString("status").equals(StatusCode.SUCCESS)) {
//                setResult(ActivityCode.REFLASH_CODE);
                finish();
            }
            }
        }
}


