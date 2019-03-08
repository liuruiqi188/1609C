package com.bw.com.liuruiqi0308.bean;

/**
 * @author liuruiqi
 * @fileName Dataaa
 * @package com.bw.com.liuruiqi0308.bean
 * @date 2019/3/8 14:48
 **/
public class Dataaa {
    private String detailUrl;
    private String price;
    private String title;

    @Override
    public String toString() {
        return "Dataaa{" +
                "detailUrl='" + detailUrl + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Dataaa(String detailUrl, String price, String title) {

        this.detailUrl = detailUrl;
        this.price = price;
        this.title = title;
    }
}
