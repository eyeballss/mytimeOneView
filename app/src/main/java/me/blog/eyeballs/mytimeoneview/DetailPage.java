package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.detail_lists.InfoViewpageAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.ImageViewpageAdapter;


public class DetailPage extends AppCompatActivity implements DataAccessible{

    int dataNumber;
    int reviewNumber;
    TextView detail_name, detail_review_count;
    ImageView detail_stars;

    ViewPager imageViewPager;
    ImageViewpageAdapter imageViewpageAdapter;

    ViewPager infoViewPager;
    InfoViewpageAdapter infoViewpageAdapter;




    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        //initialize
        init();
        //list setting
        setViewPager();
        //set the data into the views
        setting();

    }

    //review number
    // 0 : no reviews
    // 1 : mytime review
    // 2 : yelp review
    private void init(){

        Intent intent = getIntent();
        dataNumber=intent.getExtras().getInt("dataNumber");
        reviewNumber= intent.getExtras().getInt("reviewNumber");

        data= datas.get(dataNumber);

        imageViewPager = (ViewPager)findViewById(R.id.detail_image_viewpager);
        if(data.getPhoto_urls().size()!=0)
            imageViewpageAdapter = new ImageViewpageAdapter(getLayoutInflater(), data.getPhoto_urls());
        else
            imageViewpageAdapter = new ImageViewpageAdapter(getLayoutInflater(), data.getDefault_photo_slate_black());

        infoViewPager = (ViewPager)findViewById(R.id.detail_info_viewpager);
        infoViewpageAdapter = new InfoViewpageAdapter(getLayoutInflater(), data, this);

        detail_name = (TextView)findViewById(R.id.detail_name);
        detail_review_count = (TextView)findViewById(R.id.detail_review_count);
        detail_stars = (ImageView)findViewById(R.id.detail_stars);

    }

    private void setViewPager(){


        imageViewPager.setAdapter(imageViewpageAdapter);
        infoViewPager.setAdapter(infoViewpageAdapter);


    }

    private void setting(){

        detail_name.setText(data.getName());

        //review number
        // 0 : no reviews
        // 1 : mytime review
        // 2 : yelp review
        if(reviewNumber==2) {
            new ShowWebImage().setImageView(detail_stars).execute(data.yelp_getRating_image_url());
            detail_review_count.setText("("+data.yelp_getReview_count()+")");
        }
        else{
            detail_stars.setVisibility(View.GONE);
            detail_review_count.setVisibility(View.GONE);
        }
    }



    public void detail_info_viewpager_onclick(View v){
        switch(v.getId()){
            case R.id.detail_info_viewpager_left_button:
                infoViewPager.setCurrentItem(0, true);
                break;
            case R.id.detail_info_viewpager_right_button:
                infoViewPager.setCurrentItem(1, true);
                break;
        }
    }

}
