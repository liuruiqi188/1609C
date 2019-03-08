package com.bw.com.shop2.bean;

import java.util.ArrayList;

/**
 * @author liuruiqi
 * @fileName Datazhong
 * @package com.bw.com.shop2.bean
 * @date 2019/3/6 15:59
 **/
public class Datazhong {
    public String name;
    public ArrayList<Dataxiao> spus;

    @Override
    public String toString() {
        return "Datazhong{" +
                "name='" + name + '\'' +
                ", spus=" + spus +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dataxiao> getSpus() {
        return spus;
    }

    public void setSpus(ArrayList<Dataxiao> spus) {
        this.spus = spus;
    }

    public Datazhong(String name, ArrayList<Dataxiao> spus) {

        this.name = name;
        this.spus = spus;
    }
}
