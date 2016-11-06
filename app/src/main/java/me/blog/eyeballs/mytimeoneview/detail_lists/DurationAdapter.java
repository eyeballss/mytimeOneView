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
public class DurationAdapter extends BaseAdapter{

    private ArrayList<String> duration;
    private LayoutInflater inflater;
    private Activity context;

    public DurationAdapter(Activity context, Data data) {
        super();

        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.duration = data.getDuration();
    }
    @Override
    public int getCount() {
        return duration.size();
    }

    @Override
    public Object getItem(int i) {
        return duration.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView durationTextView ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.detail_list_item_duration, null);

            holder.durationTextView  = (TextView) convertView.findViewById(R.id.detail_list_item_duration_textview);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.durationTextView.setText(duration.get(i));
        return convertView;
    }
}
