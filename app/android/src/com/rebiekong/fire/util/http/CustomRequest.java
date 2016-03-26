package com.rebiekong.fire.util.http;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created by 18029 on 2015/12/26.
 */
public class CustomRequest extends Request<Response> {

    private com.android.volley.Response.Listener<Response> listener;
    private Map<String, String> params;

    public CustomRequest(int method, String url, Map<String, String> params,
                         com.android.volley.Response.Listener<Response> reponseListener, com.android.volley.Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.params = params;
    }

    @Override
    protected com.android.volley.Response<Response> parseNetworkResponse(NetworkResponse paramNetworkResponse) {
        String data = new String(paramNetworkResponse.data);
        Response response = new Response();
        response.setData(data);
        Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(paramNetworkResponse);
        return com.android.volley.Response.success(response, entry);
    }

    @Override
    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    }

    ;

    @Override
    protected void deliverResponse(Response response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }
}
