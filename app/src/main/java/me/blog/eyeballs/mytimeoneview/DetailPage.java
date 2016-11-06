package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.detail_lists.DurationAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.NextAppointmentTimesAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.ServiceNamesAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.ViewpageAdapter;


public class DetailPage extends AppCompatActivity implements DataAccessible{

    //이미지 보여주기 위해서 리스트뷰를 만들자. 그게 낫겠다. 혹은 클릭했을 때 새로운 창을 띄워서 보여주던가.
    int dataNumber;
    int reviewNumber;
    TextView detail_name, detail_review_count, detail_description_textview, detail_description_title_textview;
    TextView detail_business_diffrences_textview, detail_learning_trade_textview,detail_why_love_textview;
    TextView detail_business_diffrences_title_textview, detail_learning_trade_title_textview, detail_why_love_title_textview;
    ImageView detail_stars;
    LinearLayout detail_background_layout;

    ViewPager viewPager;
    ViewpageAdapter viewpageAdapter;

    ServiceNamesAdapter serviceNamesAdapter;
    ListView serviceNamesList;
    DurationAdapter durationAdapter;
    ListView durationList;
    NextAppointmentTimesAdapter nextAppointmentTimesAdapter;
    ListView nextAppointmentTimesList;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        //initialize
        init();
        //list setting
        setList();
        //set the data into the views
        setting();
        //to show the descriptors or not by click
        setClickListener();

    }

    //review number
    // 0 : no reviews
    // 1 : mytime review
    // 2 : yelp review
    private void init(){

        detail_background_layout = (LinearLayout)findViewById(R.id.detail_background_layout);
        Intent intent = getIntent();
        dataNumber=intent.getExtras().getInt("dataNumber");
        reviewNumber= intent.getExtras().getInt("reviewNumber");

        data= datas.get(dataNumber);

        viewPager = (ViewPager)findViewById(R.id.detail_viewpager);
        viewpageAdapter= new ViewpageAdapter(getLayoutInflater(), data.getPhoto_urls());

        detail_name = (TextView)findViewById(R.id.detail_name);
        detail_review_count = (TextView)findViewById(R.id.detail_review_count);
        detail_stars = (ImageView)findViewById(R.id.detail_stars);
        detail_description_textview = (TextView)findViewById(R.id.detail_description_textview);
        detail_description_title_textview = (TextView)findViewById(R.id.detail_description_title_textview);
        detail_business_diffrences_textview = (TextView)findViewById(R.id.detail_business_diffrences_textview);
        detail_learning_trade_textview = (TextView)findViewById(R.id.detail_learning_trade_textview);
        detail_why_love_textview = (TextView)findViewById(R.id.detail_why_love_textview);
        detail_business_diffrences_title_textview = (TextView)findViewById(R.id.detail_business_diffrences_title_textview);
        detail_learning_trade_title_textview = (TextView)findViewById(R.id.detail_learning_trade_title_textview);
        detail_why_love_title_textview = (TextView)findViewById(R.id.detail_why_love_title_textview);

    }

    private void setList(){
        serviceNamesAdapter = new ServiceNamesAdapter(this, data);
        serviceNamesList = (ListView) findViewById(R.id.detail_service_names_listview);
        serviceNamesList.setAdapter(serviceNamesAdapter);

        durationAdapter = new DurationAdapter(this, data);
        durationList = (ListView) findViewById(R.id.detail_duration_listview);
        durationList.setAdapter(durationAdapter);

        viewPager.setAdapter(viewpageAdapter);

        nextAppointmentTimesAdapter = new NextAppointmentTimesAdapter(this, data);
        nextAppointmentTimesList = (ListView)findViewById(R.id.detail_next_appointment_times_listview);
        nextAppointmentTimesList.setAdapter(nextAppointmentTimesAdapter);
    }

    private void setting(){
        new ShowWebImage().setImageView(detail_background_layout).execute(data.getDefault_photo_slate_black());

        detail_name.setText(data.getName());

        if(data.getDescription()!=null && !data.getDescription().trim().equals("")) {
            detail_description_title_textview.setText("About " + data.getName()+" ▼");
            detail_description_textview.setText(data.getDescription());
        }else{
            detail_description_textview.setVisibility(View.GONE);
            detail_description_title_textview.setVisibility(View.GONE);
        }

        if(data.getBusiness_diffrences()!=null && !data.getBusiness_diffrences().trim().equals("") ){
            detail_business_diffrences_textview.setText(data.getBusiness_diffrences());
        }else{
            detail_business_diffrences_title_textview.setVisibility(View.GONE);
            detail_business_diffrences_textview.setVisibility(View.GONE);
        }

        if(data.getLearning_trade() !=null && !data.getLearning_trade().trim().equals("")){
            detail_learning_trade_textview.setText(data.getLearning_trade());
        }else{
            detail_learning_trade_textview.setVisibility(View.GONE);
            detail_learning_trade_title_textview.setVisibility(View.GONE);
        }

        if(data.getWhy_love() !=null && !data.getWhy_love().trim().equals("")){
            detail_why_love_textview.setText(data.getWhy_love());
        }else{
            detail_why_love_textview.setVisibility(View.GONE);
            detail_why_love_title_textview.setVisibility(View.GONE);
        }

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


    private void setClickListener(){

        detail_description_textview.setVisibility(View.GONE);
        detail_business_diffrences_textview.setVisibility(View.GONE);
        detail_learning_trade_textview.setVisibility(View.GONE);
        detail_why_love_textview.setVisibility(View.GONE);

        detail_business_diffrences_title_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail_business_diffrences_textview.getVisibility()!=view.VISIBLE)
                    detail_business_diffrences_textview.setVisibility(View.VISIBLE);
                else detail_business_diffrences_textview.setVisibility(View.GONE);
            }
        });
        detail_learning_trade_title_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail_learning_trade_textview.getVisibility()!=view.VISIBLE)
                    detail_learning_trade_textview.setVisibility(View.VISIBLE);
                else detail_learning_trade_textview.setVisibility(View.GONE);
            }
        });
        detail_why_love_title_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail_why_love_textview.getVisibility()!=view.VISIBLE)
                    detail_why_love_textview.setVisibility(View.VISIBLE);
                else detail_why_love_textview.setVisibility(View.GONE);
            }
        });
        detail_description_title_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(detail_description_textview.getVisibility()!=view.VISIBLE)
                    detail_description_textview.setVisibility(View.VISIBLE);
                else detail_description_textview.setVisibility(View.GONE);
            }
        });

    }
}