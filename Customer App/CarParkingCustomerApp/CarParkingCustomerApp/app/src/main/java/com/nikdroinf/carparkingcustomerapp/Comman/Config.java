package com.nikdroinf.carparkingcustomerapp.Comman;

public class Config {

    private static String OnlineAddress = "http:// 172.16.102.11:80/customerapp/";
    public static String OnlineImageAddress = "http:// 172.16.102.11:80/smart_home_delivery/restaurantapp/upload/";
    //private static String OnlineAddress = "http://192.168.43.87:80/car_parking/customerapp/";
    //public static String OnlineImageAddress = "http://192.168.43.87:80/smart_home_delivery/restaurantapp/upload/";

    public static String url_addusers = OnlineAddress+ "registerUser.php";
    public static String url_login = OnlineAddress+ "loginUser.php";
    public static String url_get_my_details = OnlineAddress+ "getMyDetails.php";
    public static String url_view_my_booking = OnlineAddress+ "getViewMyBooking.php";
    public static String url_add_my_feedback = OnlineAddress+ "addMyFeedback.php";


}
