package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.detail_lists.DurationAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.NextAppointmentTimesAdapter;
import me.blog.eyeballs.mytimeoneview.detail_lists.ServiceNamesAdapter;


public class DetailPage extends AppCompatActivity implements DataAccessible{

    //이미지 보여주기 위해서 리스트뷰를 만들자. 그게 낫겠다. 혹은 클릭했을 때 새로운 창을 띄워서 보여주던가.
    int dataNumber;
    int reviewNumber;
    TextView detail_name, detail_review_count, detail_about_textview, detail_description_textview;
    TextView detail_business_diffrences_textview, detail_learning_trade_textview,detail_why_love_textview;
    TextView detail_business_diffrences_title_textview, detail_learning_trade_title_textview, detail_why_love_title_textview;
    ImageView detail_stars;

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


        init();
        setList();
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

        detail_name = (TextView)findViewById(R.id.detail_name);
        detail_review_count = (TextView)findViewById(R.id.detail_review_count);
        detail_stars = (ImageView)findViewById(R.id.detail_stars);
        detail_about_textview = (TextView)findViewById(R.id.detail_about_textview);
        detail_description_textview = (TextView)findViewById(R.id.detail_description_textview);
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

        nextAppointmentTimesAdapter = new NextAppointmentTimesAdapter(this, data);
        nextAppointmentTimesList = (ListView)findViewById(R.id.detail_next_appointment_times_listview);
        nextAppointmentTimesList.setAdapter(nextAppointmentTimesAdapter);
    }

    private void setting(){
        detail_name.setText(data.getName());
        if(reviewNumber==1) detail_review_count.setText("("+data.mytime_getReview_count()+")");
        else if(reviewNumber==2)detail_review_count.setText("("+data.yelp_getReview_count()+")");
        else detail_review_count.setText("");

        if(data.getName()!=null && !data.getName().equals("")) {
            detail_about_textview.setText("About " + data.getName());
            detail_description_textview.setText(data.getDescription());
        }else{
            detail_about_textview.setText("");
            detail_description_textview.setText("");
        }

        if(data.getBusiness_diffrences()!=null && !data.getBusiness_diffrences().equals("") ){
            detail_business_diffrences_textview.setText(data.getBusiness_diffrences());
        }else{
            detail_business_diffrences_title_textview.setText("");
            detail_business_diffrences_textview.setText("");
        }

        if(data.getLearning_trade() !=null && !data.getLearning_trade().equals("")){
            detail_learning_trade_textview.setText(data.getLearning_trade());
        }else{
            detail_learning_trade_textview.setText("");
            detail_business_diffrences_title_textview.setText("");
        }

        if(data.getWhy_love() !=null && !data.getWhy_love().equals("")){
            detail_why_love_textview.setText(data.getWhy_love());
        }else{
            detail_why_love_textview.setText("");
            detail_why_love_title_textview.setText("");
        }

        if(data.getWhy_love() !=null && !data.getWhy_love().equals("")){
            detail_why_love_textview.setText(data.getWhy_love());
        }else{
            detail_why_love_textview.setText("");
            detail_why_love_title_textview.setText("");
        }
    }

}
