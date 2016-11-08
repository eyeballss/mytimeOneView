package me.blog.eyeballs.mytimeoneview.detail_lists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.blog.eyeballs.mytimeoneview.Data;
import me.blog.eyeballs.mytimeoneview.R;

/**
 * Created by eye on 16. 11. 2.
 */
public class NextAppointmentTimesAdapter extends BaseAdapter {
    private ArrayList<String> nextAppointmentTimes;
    private LayoutInflater inflater;

    public NextAppointmentTimesAdapter(Activity context, Data data) {
        super();

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.nextAppointmentTimes = data.getNext_appointment_times();
    }

    @Override
    public int getCount() {
        return nextAppointmentTimes.size();
    }

    @Override
    public Object getItem(int i) {
        return nextAppointmentTimes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView nextAppointmentTimesTextView ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.detail_list_item_next_appointment_times, null);

            holder.nextAppointmentTimesTextView  = (TextView) convertView.findViewById(R.id.detail_list_item_next_appointment_times_textview);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String str = nextAppointmentTimes.get(i).replaceAll("T", " ").replaceAll("Z", " ");
        holder.nextAppointmentTimesTextView.setText(str);

        return convertView;
    }
}
