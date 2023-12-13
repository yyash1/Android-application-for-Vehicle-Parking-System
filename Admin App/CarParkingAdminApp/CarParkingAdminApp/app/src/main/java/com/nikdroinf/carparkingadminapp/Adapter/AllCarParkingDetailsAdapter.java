package com.nikdroinf.carparkingadminapp.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nikdroinf.carparkingadminapp.Comman.Config;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_All_Car_Parking_Details;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingadminapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class AllCarParkingDetailsAdapter extends BaseAdapter {

    List<pojo_All_Car_Parking_Details> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public AllCarParkingDetailsAdapter(List<pojo_All_Car_Parking_Details> list, Activity activity, TextView tv_no_record) {
        this.list = list;
        this.activity = activity;
        this.tv_no_record = tv_no_record;

        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {

        final AllCarParkingDetailsAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new AllCarParkingDetailsAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_all_car_parking_data,null);

            holder.book_vehincal_no = v.findViewById(R.id.txt_book_vehical_no);
            holder.plot_name = v.findViewById(R.id.txt_book_vehical_plot_name);
            holder.slot_name = v.findViewById(R.id.txt_book_vehical_slot_name);
            holder.start_date_of_booking = v.findViewById(R.id.txt_book_vehical_start_date);
            holder.end_date_of_booking = v.findViewById(R.id.txt_book_vehical_end_date);
            holder.start_time_of_booking = v.findViewById(R.id.txt_book_vehical_start_time);
            holder.end_time_of_booking = v.findViewById(R.id.txt_book_vehical_end_time);

            v.setTag(holder);
        }else
        {
            holder = (AllCarParkingDetailsAdapter.ViewHolder) v.getTag();
        }

        final pojo_All_Car_Parking_Details obj = list.get(position);
        holder.book_vehincal_no.setText(obj.getVehincal_no());
        holder.plot_name.setText(obj.getPlot_name());
        holder.slot_name.setText(obj.getSlot_name());
        holder.start_date_of_booking.setText(obj.getDate_of_booking());
        holder.end_date_of_booking.setText(obj.getEnd_date_of_booking());
        holder.start_time_of_booking.setText(obj.getTime_of_booking());
        holder.end_time_of_booking.setText(obj.getEnd_time_of_booking());
        return v;
    }



    class ViewHolder
    {
        TextView book_vehincal_no,plot_name,slot_name,start_date_of_booking,end_date_of_booking,start_time_of_booking,end_time_of_booking;
    }
}
