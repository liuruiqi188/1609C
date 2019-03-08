package com.bw.com.shop2.bean;

/**
 * @author liuruiqi
 * @fileName Dataxiao
 * @package com.bw.com.shop2.bean
 * @date 2019/3/6 15:57
 **/
public class Dataxiao {
    public String name;
    public String praise_num;
     public String pic_url;

    @Override
    public String toString() {
        return "Dataxiao{" +
                "name='" + name + '\'' +
                ", praise_num='" + praise_num + '\'' +
                ", pic_url='" + pic_url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Dataxiao(String name, String praise_num, String pic_url) {

        this.name = name;
        this.praise_num = praise_num;
        this.pic_url = pic_url;
    }
}
