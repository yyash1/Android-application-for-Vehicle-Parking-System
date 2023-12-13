package com.nikdroinf.carparkingadminapp.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nikdroinf.carparkingadminapp.PojoClass.pojo_All_Feedback;
import com.nikdroinf.carparkingadminapp.PojoClass.pojo_View_Booking;
import com.nikdroinf.carparkingadminapp.R;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {

    List<pojo_All_Feedback> list;
    Activity activity;
    TextView tv_no_record;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public FeedbackAdapter(List<pojo_All_Feedback> list, Activity activity, TextView tv_no_record) {
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

        final FeedbackAdapter.ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (v == null)
        {
            holder = new FeedbackAdapter.ViewHolder();
            v = inflater.inflate(R.layout.list_view_all_feedback,null);

            holder.vehical_no = v.findViewById(R.id.txt_feedback_person_vehicle_no);
            holder.mobile_no = v.findViewById(R.id.txt_feedback_person_mobile_no);
            holder.feedback = v.findViewById(R.id.txt_feedback_person_feedback);

            v.setTag(holder);
        }else
        {
            holder = (FeedbackAdapter.ViewHolder) v.getTag();
        }

        final pojo_All_Feedback obj = list.get(position);
        holder.vehical_no.setText(obj.getVehical_no());
        holder.mobile_no.setText(obj.getMobile_no());
        holder.feedback.setText(obj.getFeedback());

        return v;
    }

    class ViewHolder
    {
        TextView vehical_no,mobile_no,feedback;
    }
}
