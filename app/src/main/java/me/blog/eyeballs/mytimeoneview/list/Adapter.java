package me.blog.eyeballs.mytimeoneview.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.blog.eyeballs.mytimeoneview.Data;
import me.blog.eyeballs.mytimeoneview.R;

/**
 * Created by eye on 16. 10. 25.
 */
public class Adapter extends BaseAdapter {

    private Activity context;
    private ArrayList<Data> datas;
    private LayoutInflater inflater;

    public Adapter(Activity context, ArrayList<Data> datas) {
        super();

        this.context = context;
        this.datas = datas;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void init(int i, View view){
        Data data = datas.get(i);
        TextView cityName = (TextView)view.findViewById(R.id.city_name);
        ImageView thumbnail = (ImageView)view.findViewById(R.id.thumbnail);

        cityName.setText(data.getDefault_photo_thumb());

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.stores_list_item, null);
        init(i, convertView);

        return convertView;
    }
}
