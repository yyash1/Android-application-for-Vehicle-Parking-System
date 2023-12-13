package com.nikdroinf.carparkingwatchmanapp.Comman;

public class Config {

    private static String OnlineAddress = "http://192.168.43.87:80/car_parking/WatchmenApp/";
    public static String OnlineImageAddress = "http://192.168.43.87:80/smart_home_delivery/restaurantapp/upload/";

    public static String url_login = OnlineAddress+ "loginUser.php";
    public static String url_add_slot = OnlineAddress+ "addCarSlot.php";
    public static String url_confirm_booking = OnlineAddress+ "addConfirmBooking.php";
    public static String url_get_booking = OnlineAddress+ "getAllBooking.php";
    public static String url_get_my_profile = OnlineAddress+ "getMyProfile.php";
    public static String url_all_view_booking = OnlineAddress+ "getAllViewBooking.php";
    public static String url_delete_booking=OnlineAddress+ "deleteBooking.php";
    public static String url_add_remove_car_details=OnlineAddress+ "addRemoveCarDetails.php";


}
