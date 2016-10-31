package me.blog.eyeballs.mytimeoneview.stores_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.Data;
import me.blog.eyeballs.mytimeoneview.DataAccessible;
import me.blog.eyeballs.mytimeoneview.DetailPage;
import me.blog.eyeballs.mytimeoneview.R;
import me.blog.eyeballs.mytimeoneview.ShowWebImage;

/**
 * Created by eye on 16. 10. 25.
 */
public class Adapter extends BaseAdapter implements DataAccessible{

    private Activity context;
    private LayoutInflater inflater;

    public Adapter(Activity context) {
        super();

        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public void init(int i, View convertView) {
        Data data = datas.get(i); //store's data

        TextView storeName = (TextView) convertView.findViewById(R.id.store_name);
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.store_thumbnail);
        TextView cityName = (TextView) convertView.findViewById(R.id.store_city_name);
        TextView serviceName = (TextView) convertView.findViewById(R.id.store_service_name);
        TextView price = (TextView) convertView.findViewById(R.id.store_price);
        ImageView stars = (ImageView) convertView.findViewById(R.id.store_stars);
        TextView reviewCount = (TextView) convertView.findViewById(R.id.store_review_count);

        storeName.setText(data.getName());
        new ShowWebImage().setImageView(thumbnail).execute(data.getDefault_photo_thumb());
        cityName.setText(data.getCity());
        serviceName.setText(data.getService_name());
        price.setText("$ "+String.valueOf(data.getMin_price())+" - $ "+String.valueOf(data.getMax_price()));

//        holder.title.setText(data.getName());
//        showWebImage.setImageView(holder.thumbnail).execute(data.getDefault_photo_thumb());
//        holder.city_name.setText(data.getCity());
//        holder.service_name.setText(data.getService_name());
//        holder.price.setText(data.getMin_price()+" - "+data.getMax_price());

        int reviewNumber=0;
        {
            int myTime=data.mytime_getReview_count();
            int yelp=data.yelp_getReview_count();

            if(myTime<0 && yelp<0){
                stars.setImageBitmap(null);
                reviewCount.setText("");
                reviewNumber =0;
            }
            else if(myTime>=yelp){
                stars.setImageBitmap(null);
                reviewCount.setText("("+data.mytime_getReview_count()+")");
                reviewNumber=1;
            }else {
                new ShowWebImage().setImageView(stars).execute(data.yelp_getRating_image_url());
                reviewCount.setText("("+data.yelp_getReview_count()+")");
                reviewNumber=2;
            }

        }

        setClickListners(i, storeName, thumbnail, reviewNumber);

    }

    private void setClickListners(final int i, TextView storeName, ImageView thumbnail, final int reviewNumber){
        storeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailPage(i, reviewNumber);
            }
        });

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailPage(i, reviewNumber);

            }
        });
    }

    private void showDetailPage(int i, int reviewNumber){

        Intent intent = new Intent(context, DetailPage.class);

        //data number : to find data in the data array.
        intent.putExtra("dataNumber", i);

        //review number : to find what company's review is valuable.
        intent.putExtra("reviewNumber", reviewNumber);
        context.startActivity(intent);

    }

    public static class ViewHolder {
        TextView title;
        ImageView thumbnail;
        TextView city_name;
        TextView service_name;
        TextView price;
        ImageView stars;
        TextView review_count;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        convertView = inflater.inflate(R.layout.stores_list_item, null);

//        ViewHolder holder;
//        if(convertView ==null){
//            holder = new ViewHolder();
//            convertView = inflater.inflate(R.layout.stores_list_item, null);
//
//            holder.title = (TextView) convertView.findViewById(R.id.title);
//            holder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
//            holder.city_name = (TextView) convertView.findViewById(R.id.city_name);
//            holder.service_name = (TextView) convertView.findViewById(R.id.service_name);
//            holder.price = (TextView) convertView.findViewById(R.id.price);
//            holder.stars = (ImageView) convertView.findViewById(R.id.stars);
//            holder.review_count = (TextView) convertView.findViewById(R.id.review_count);
//
//
//            convertView.setTag(holder);
//        }
//        else{
//            holder = (ViewHolder)convertView.getTag();
//        }

        init(i, convertView);

        return convertView;
    }
}
