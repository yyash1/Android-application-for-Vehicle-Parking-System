package com.nikdroinf.carparkingadminapp.PojoClass;

public class pojo_All_Users {

    String vehical_no,name,mobile_no,email_id;

    public pojo_All_Users(String vehical_no, String name, String mobile_no, String email_id) {
        this.vehical_no = vehical_no;
        this.name = name;
        this.mobile_no = mobile_no;
        this.email_id = email_id;
    }

    public String getVehical_no() {
        return vehical_no;
    }

    public void setVehical_no(String vehical_no) {
        this.vehical_no = vehical_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
