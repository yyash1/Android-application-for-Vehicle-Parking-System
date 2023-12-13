package com.nikdroinf.carparkingcustomerapp.AdapterClass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nikdroinf.carparkingcustomerapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingcustomerapp.R;

import java.util.List;

public class ViewBookingAdapter extends BaseAdapter {

    List<pojo_View_Booking> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ViewBookingAdapter(List<pojo_View_Booking> list, Activity activity, TextView tv_no_record) {
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
    public View getView(int position, View v, ViewGroup parent) {

        final ViewBookingAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new ViewBookingAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_view_booking,null);

            holder.book_vehincal_no = v.findViewById(R.id.txt_book_vehical_no);
            holder.plot_name = v.findViewById(R.id.txt_book_vehical_plot_name);
            holder.slot_name = v.findViewById(R.id.txt_book_vehical_slot_name);
            holder.date_of_booking = v.findViewById(R.id.txt_book_vehical_date);
            holder.time_of_booking = v.findViewById(R.id.txt_book_vehical_time);

            v.setTag(holder);
        }else
        {
            holder = (ViewBookingAdapter.ViewHolder) v.getTag();
        }

        final pojo_View_Booking obj = list.get(position);
        holder.book_vehincal_no.setText(obj.getBook_vehincal_no());
        holder.plot_name.setText(obj.getPlot_name());
        holder.slot_name.setText(obj.getSlot_name());
        holder.date_of_booking.setText(obj.getDate_of_booking());
        holder.time_of_booking.setText(obj.getTime_of_booking());

        return v;
    }

    class ViewHolder
    {
        TextView book_vehincal_no,plot_name,slot_name,date_of_booking,time_of_booking;
    }


}
