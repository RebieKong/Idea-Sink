package com.rebiekong.fire.util.http;

import com.alibaba.fastjson.JSON;
import com.rebiekong.fire.entity.BasicBean;

/**
 * Created by 18029 on 2015/12/25.
 */
public class Response {
    private String data;
    private BasicBean bean;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        bean = JSON.parseObject(getData(), BasicBean.class);
    }

    public BasicBean getBean(){
        return bean;
    }

}
