package com.nikdroinf.carparkingwatchmanapp.AdapterClass;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.Image;
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
import com.nikdroinf.carparkingwatchmanapp.Comman.Config;
import com.nikdroinf.carparkingwatchmanapp.PojoClass.pojo_View_Booking_For_Remove;
import com.nikdroinf.carparkingwatchmanapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ViewBookingForRemoveAdapter extends BaseAdapter {

    List<pojo_View_Booking_For_Remove> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ViewBookingForRemoveAdapter(List<pojo_View_Booking_For_Remove> list, Activity activity, TextView tv_no_record) {
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

        final ViewBookingForRemoveAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new ViewBookingForRemoveAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_view_booking_for_remove,null);

            holder.book_vehincal_no = v.findViewById(R.id.txt_book_vehical_no);
            holder.plot_name = v.findViewById(R.id.txt_book_vehical_plot_name);
            holder.slot_name = v.findViewById(R.id.txt_book_vehical_slot_name);
            holder.date_of_booking = v.findViewById(R.id.txt_book_vehical_date);
            holder.time_of_booking = v.findViewById(R.id.txt_book_vehical_time);
            holder.delete = v.findViewById(R.id.ic_delete);

            v.setTag(holder);
        }else
        {
            holder = (ViewBookingForRemoveAdapter.ViewHolder) v.getTag();
        }

        final pojo_View_Booking_For_Remove obj = list.get(position);
        holder.book_vehincal_no.setText(obj.getBook_vehincal_no());
        holder.plot_name.setText(obj.getPlot_name());
        holder.slot_name.setText(obj.getSlot_name());
        holder.date_of_booking.setText(obj.getDate_of_booking());
        holder.time_of_booking.setText(obj.getTime_of_booking());

        editor.putString("vehicle_no",obj.getBook_vehincal_no()).commit();
        editor.putString("plot_name",obj.getPlot_name()).commit();
        editor.putString("slot_name",obj.getSlot_name()).commit();
        editor.putString("date_of_booking",obj.getDate_of_booking()).commit();
        editor.putString("time_of_booking",obj.getTime_of_booking()).commit();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(activity);
                ad.setTitle("")
                        .setMessage("Are You Sure You Want To Remove")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletemarketrate(Integer.parseInt(obj.getId()),position);
                            }
                        });

                AlertDialog alertDialog = ad.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.darker_gray);
            }
        });

        return v;
    }

    private void deletemarketrate(int id, final int position) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id", ""+id);
        client.post(Config.url_delete_booking, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String aa = response.getString("success");

                    if (aa.equals("1")) {
                        list.remove(position);
                        notifyDataSetChanged();
                        getallremovedata();
                    } else {
                        //  Toast.makeText(AddEventActivity.this, "Please Select all fields", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                // progress.setVisibility(View.GONE);
                // Toast.makeText(AddEventActivity.this, "Could Not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }//

    private void getallremovedata() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("vehicle_no",preferences.getString("vehicle_no",""));
        params.put("plot_name",preferences.getString("plot_name",""));
        params.put("slot_name",preferences.getString("slot_name",""));
        params.put("date_of_booking",preferences.getString("date_of_booking",""));
        params.put("time_of_booking",preferences.getString("time_of_booking",""));


        client.post(Config.url_add_remove_car_details,params,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String aa = response.getString("success");

                    if (aa.equals("1"))
                    {
                        Toast.makeText(activity, "Remove Succesfully", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(activity, "Already Remove", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(activity, "Could not Connect", Toast.LENGTH_SHORT).show();
            }
        });

    }


    class ViewHolder
    {
        TextView book_vehincal_no,plot_name,slot_name,date_of_booking,time_of_booking;
        ImageView delete;
        ProgressBar progress;
    }

}
