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
        return searchResultList.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(searchResultList.get(i));
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void init(int i, View convertView) {
        Data data = datas.get(searchResultList.get(i)); //store's data

        TextView storeName = (TextView) convertView.findViewById(R.id.store_name);
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.store_thumbnail);
        TextView cityName = (TextView) convertView.findViewById(R.id.store_city_name);
        TextView serviceName = (TextView) convertView.findViewById(R.id.store_service_name);
        TextView price = (TextView) convertView.findViewById(R.id.store_price);
        ImageView stars = (ImageView) convertView.findViewById(R.id.store_stars);
        TextView reviewCount = (TextView) convertView.findViewById(R.id.store_review_count);

        storeName.setText(data.getName());
        storeName.setSelected(true);
        new ShowWebImage().setImageView(thumbnail).execute(data.getDefault_photo_thumb());
        cityName.setText(data.getCity());
        serviceName.setText(data.getService_name());
        price.setText("$ "+String.valueOf(data.getMin_price())+" - $ "+String.valueOf(data.getMax_price()));


        //review number
        // 0 : no reviews
        // 1 : mytime review
        // 2 : yelp review
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
        intent.putExtra("dataNumber", searchResultList.get(i));

        //review number : to find what company's review is valuable.
        intent.putExtra("reviewNumber", reviewNumber);
        context.startActivity(intent);

    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        convertView = inflater.inflate(R.layout.stores_list_item, null);

        init(i, convertView);

        return convertView;
    }
}
