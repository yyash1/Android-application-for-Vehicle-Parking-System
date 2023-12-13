package com.nikdroinf.carparkingadminapp.PojoClass;

public class pojo_All_Feedback {

    String vehical_no,mobile_no,feedback;

    public pojo_All_Feedback(String vehical_no, String mobile_no, String feedback) {
        this.vehical_no = vehical_no;
        this.mobile_no = mobile_no;
        this.feedback = feedback;
    }

    public String getVehical_no() {
        return vehical_no;
    }

    public void setVehical_no(String vehical_no) {
        this.vehical_no = vehical_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
