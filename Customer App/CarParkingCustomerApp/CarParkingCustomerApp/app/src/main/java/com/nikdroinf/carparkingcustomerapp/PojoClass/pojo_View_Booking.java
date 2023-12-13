package com.nikdroinf.carparkingcustomerapp.PojoClass;

public class pojo_View_Booking {

    String book_vehincal_no,plot_name,slot_name,date_of_booking,time_of_booking;

    public pojo_View_Booking(String book_vehincal_no, String plot_name, String slot_name, String date_of_booking, String time_of_booking) {
        this.book_vehincal_no = book_vehincal_no;
        this.plot_name = plot_name;
        this.slot_name = slot_name;
        this.date_of_booking = date_of_booking;
        this.time_of_booking = time_of_booking;
    }

    public String getBook_vehincal_no() {
        return book_vehincal_no;
    }

    public void setBook_vehincal_no(String book_vehincal_no) {
        this.book_vehincal_no = book_vehincal_no;
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

    public String getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(String time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

}
