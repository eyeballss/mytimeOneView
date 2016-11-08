package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.detail_lists.ImageViewpageAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.InfoViewpageAdapter;


public class DetailPage extends AppCompatActivity implements DataAccessible{

    int dataNumber;
    int reviewNumber;
    TextView detail_name, detail_review_count;
    ImageView detail_stars;

    ViewPager imageViewPager;
    ImageViewpageAdapter imageViewpageAdapter;

    ViewPager infoViewPager;
    InfoViewpageAdapter infoViewpageAdapter;

    Button detail_website_button;



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

        detail_website_button = (Button) findViewById(R.id.detail_website_button);
    }

    private void setViewPager(){


        imageViewPager.setAdapter(imageViewpageAdapter);
        infoViewPager.setAdapter(infoViewpageAdapter);


    }

    private void setting(){

        detail_name.setText(data.getName());
        detail_name.setSelected(true);

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
    public void detail_info_bottom_button_onclick(View v){
        switch(v.getId()){
            case R.id.detail_website_button:
                String website = "";

                if (data.getWebsite() != null && !data.getWebsite().equals("")) {
                    website = data.getWebsite();
                } else if (data.getBitly_url() != null && !data.getBitly_url().equals("")) {
                    website = data.getBitly_url();
                } else {
                    website = "https://www.google.com/search?q=site:www.mytime.com+";
                    website += data.getName().replaceAll(" ", "+");
                }

                Intent intentForWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                startActivity(intentForWeb);


                break;
        }
    }
}


