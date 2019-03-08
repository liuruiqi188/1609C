package com.bw.com.shop2.bean;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName Json
 * @package com.bw.com.shop2.bean
 * @date 2019/3/6 16:01
 **/
public class Json {
    public ArrayList<Datazhong> data;

    @Override
    public String toString() {
        return "Json{" +
                "data=" + data +
                '}';
    }

    public ArrayList<Datazhong> getData() {
        return data;
    }

    public void setData(ArrayList<Datazhong> data) {
        this.data = data;
    }

    public Json(ArrayList<Datazhong> data) {

        this.data = data;
    }
}
