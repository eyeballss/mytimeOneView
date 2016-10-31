package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailPage extends AppCompatActivity implements DataAccessible{

    //이미지 보여주기 위해서 리스트뷰를 만들자. 그게 낫겠다. 혹은 클릭했을 때 새로운 창을 띄워서 보여주던가.

    int dataNumber;
    int reviewNumber;
    TextView detail_name, detail_review_count, detail_service_names,
            detail_duration, detail_available_times;
    ImageView detail_stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Intent intent = getIntent();
        dataNumber=intent.getExtras().getInt("dataNumber");
        reviewNumber= intent.getExtras().getInt("reviewNumber");

        init();

        setting();

    }

    private void init(){
        detail_name = (TextView)findViewById(R.id.detail_name);
        detail_review_count = (TextView)findViewById(R.id.detail_review_count);
        detail_service_names= (TextView)findViewById(R.id.detail_service_names);
        detail_duration= (TextView)findViewById(R.id.detail_duration);
        detail_available_times= (TextView)findViewById(R.id.detail_available_times);
        detail_stars = (ImageView)findViewById(R.id.detail_stars);
    }

    private void setting(){

    }

}
