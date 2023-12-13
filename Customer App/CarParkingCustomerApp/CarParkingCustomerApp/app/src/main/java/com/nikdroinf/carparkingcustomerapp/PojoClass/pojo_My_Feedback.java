package com.nikdroinf.carparkingcustomerapp.PojoClass;

public class pojo_My_Feedback {

    String vehical_no,user_name,feedback;

    public pojo_My_Feedback(String vehical_no, String user_name, String feedback) {
        this.vehical_no = vehical_no;
        this.user_name = user_name;
        this.feedback = feedback;
    }

    public String getVehical_no() {
        return vehical_no;
    }

    public void setVehical_no(String vehical_no) {
        this.vehical_no = vehical_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
