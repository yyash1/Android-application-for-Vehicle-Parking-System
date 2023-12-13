package com.nikdroinf.carparkingadminapp.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nikdroinf.carparkingadminapp.PojoClass.pojo_All_Users;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingadminapp.R;

import java.util.List;

public class AllUsersAdapter extends BaseAdapter {

    List<pojo_All_Users> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public AllUsersAdapter(List<pojo_All_Users> list, Activity activity, TextView tv_no_record) {
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

        final AllUsersAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new AllUsersAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_all_users,null);

            holder.vehical_no = v.findViewById(R.id.txt_user_vehical_no);
            holder.name = v.findViewById(R.id.txt_person_name);
            holder.mobile_no = v.findViewById(R.id.txt_person_mobile_no);
            holder.email_id = v.findViewById(R.id.txt_person_email_id);


            v.setTag(holder);
        }else
        {
            holder = (AllUsersAdapter.ViewHolder) v.getTag();
        }

        final pojo_All_Users obj = list.get(position);
        holder.vehical_no.setText(obj.getVehical_no());
        holder.name.setText(obj.getName());
        holder.mobile_no.setText(obj.getMobile_no());
        holder.email_id.setText(obj.getEmail_id());

        return v;
    }

    class ViewHolder
    {
        TextView vehical_no,name,mobile_no,email_id;
    }
}
