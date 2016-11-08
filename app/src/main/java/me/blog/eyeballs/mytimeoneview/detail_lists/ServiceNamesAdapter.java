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
public class ServiceNamesAdapter extends BaseAdapter {

    private ArrayList<String> serviceNames;
    private LayoutInflater inflater;

    public ServiceNamesAdapter(Activity context, Data data) {
        super();

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.serviceNames = data.getServiceNames();
    }

    @Override
    public int getCount() {
        return serviceNames.size();
    }

    @Override
    public Object getItem(int i) {
        return serviceNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView serviceNameTextView;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.detail_list_item_service_names, null);

            holder.serviceNameTextView = (TextView) convertView.findViewById(R.id.detail_list_item_service_names_textview);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.serviceNameTextView.setText(serviceNames.get(i));
        return convertView;
    }
}
