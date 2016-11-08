package me.blog.eyeballs.mytimeoneview;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


    private void call() {


                /**
                 *  현재 사용자의 OS버전이 마시멜로우 인지 체크한다.
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    /**
                     *  사용자 단말기의 권한 중 전화걸기 권한이 허용되어 있는지 체크한다.
                     */
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);

                    // call_phong의 권한이 없을 떄
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        //  Package는 Android Application의 ID이다.
                        /**
                         *  사용자가 CALL_PHONE 권한을 한번이라도 거부한 적이 있는지 조사한다.
                         *  거부한 이력이 한번이라도 있다면, true를 리턴한다.
                         *  거부한 이력이 없다면 false를 리턴한다.
                         */
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                            dialog.setTitle("권한이 필요합니다.")
                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\"권한이 필요합니다. 계속하시겠습니까?")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 위 리스너랑 다른 범위여서 마쉬멜로우인지 또 체크해주어야 한다.
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext(), "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .create()
                                    .show();

                        }
                        // 최초로 권한을 요청 할 때
                        else {
                            // CALL_PHONE 권한을 안드로이드 OS에 요청합니다.
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    // call_phonne의 권한이 있을 떄
                    else {
                        if (data.getPhone_number() != null && !data.getPhone_number().equals("")) {
                            Intent intentForCall= new Intent(Intent.ACTION_CALL, Uri.parse(data.getPhone_number()) );
                            startActivity(intentForCall);
                        } else {
                            Toast.makeText(this, "There is no phone number.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                // 사용자의 버전이 마시멜로우 이하일때
                else {

                    if (data.getPhone_number() != null && !data.getPhone_number().equals("")) {
                        Intent intentForCall= new Intent(Intent.ACTION_CALL, Uri.parse(data.getPhone_number()) );
                        startActivity(intentForCall);
                    } else {
                        Toast.makeText(this, "There is no phone number.", Toast.LENGTH_SHORT).show();
                    }
                }


    }


}


