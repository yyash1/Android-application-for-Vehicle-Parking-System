package com.nikdroinf.carparkingadminapp.PojoClass;

public class pojo_All_Car_Parking_Details {

    String id,vehincal_no, plot_name, slot_name, date_of_booking,end_date_of_booking, time_of_booking,end_time_of_booking;

    public pojo_All_Car_Parking_Details(String id, String vehincal_no, String plot_name, String slot_name, String date_of_booking, String end_date_of_booking, String time_of_booking, String end_time_of_booking) {        this.id = id;
        this.vehincal_no = vehincal_no;
        this.plot_name = plot_name;
        this.slot_name = slot_name;
        this.date_of_booking = date_of_booking;
        this.end_date_of_booking = end_date_of_booking;
        this.time_of_booking = time_of_booking;
        this.end_time_of_booking = end_time_of_booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehincal_no() {
        return vehincal_no;
    }

    public void setVehincal_no(String vehincal_no) {
        this.vehincal_no = vehincal_no;
    }

    public String getPlot_name() {
        return plot_name;
    }

    public void setPlot_name(String plot_name) {
        this.plot_name = plot_name;
    }

    public String getSlot_name() {
        return slot_name;
    }

    public void setSlot_name(String slot_name) {
        this.slot_name = slot_name;
    }

    public String getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(String date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public String getEnd_date_of_booking() {
        return end_date_of_booking;
    }

    public void setEnd_date_of_booking(String end_date_of_booking) {
        this.end_date_of_booking = end_date_of_booking;
    }

    public String getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(String time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

    public String getEnd_time_of_booking() {
        return end_time_of_booking;
    }

    public void setEnd_time_of_booking(String end_time_of_booking) {
        this.end_time_of_booking = end_time_of_booking;
    }
}
