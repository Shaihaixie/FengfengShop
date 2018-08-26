package com.neuedu.entity;

import java.io.Serializable;

public class LogBean implements Serializable {
    private  int id;
    private  int  userid;
    private  String messs;
   public  LogBean(int userid,String messs){
          this.userid=userid;
          this.messs=messs;
   }
    @Override
    public String toString() {
        return "LogBean{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", messs='" + messs + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMesss() {
        return messs;
    }

    public void setMesss(String messs) {
        this.messs = messs;
    }
}
