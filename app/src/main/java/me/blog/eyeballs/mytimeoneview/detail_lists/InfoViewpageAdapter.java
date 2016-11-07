package me.blog.eyeballs.mytimeoneview.detail_lists;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import me.blog.eyeballs.mytimeoneview.Data;
import me.blog.eyeballs.mytimeoneview.R;

/**
 * Created by eye on 16. 11. 7.
 */
public class InfoViewpageAdapter extends PagerAdapter {


    TextView detail_description_textview, detail_description_title_textview;
    TextView detail_business_diffrences_textview, detail_learning_trade_textview,detail_why_love_textview;
    TextView detail_business_diffrences_title_textview, detail_learning_trade_title_textview, detail_why_love_title_textview;

    ServiceNamesAdapter serviceNamesAdapter;
    ListView serviceNamesList;
    DurationAdapter durationAdapter;
    ListView durationList;
    NextAppointmentTimesAdapter nextAppointmentTimesAdapter;
    ListView nextAppointmentTimesList;

    LayoutInflater inflater;
    Activity activity;

    Data data;

    public InfoViewpageAdapter(LayoutInflater inflater, Data data, Activity activity) {
        // TODO Auto-generated constructor stub
        this.data = data;
        this.inflater=inflater;
        this.activity=activity;
    }



    private void bookInformationPage(View view){
        init(view, 0);
        setting(view, 0);
    }

    private void aboutPage(View view){
        init(view, 1);
        setting(view, 1);
        //to show the descriptors or not by click
        setClickListener(view, 1);
    }

    private void init(View view , int page){
        switch (page) {
            //left page, bookInformationPage
            case 0:
                serviceNamesAdapter = new ServiceNamesAdapter(activity, data);
                serviceNamesList = (ListView) view.findViewById(R.id.detail_service_names_listview);
                serviceNamesList.setAdapter(serviceNamesAdapter);

                durationAdapter = new DurationAdapter(activity, data);
                durationList = (ListView) view.findViewById(R.id.detail_duration_listview);
                durationList.setAdapter(durationAdapter);

                nextAppointmentTimesAdapter = new NextAppointmentTimesAdapter(activity, data);
                nextAppointmentTimesList = (ListView)view.findViewById(R.id.detail_next_appointment_times_listview);
                nextAppointmentTimesList.setAdapter(nextAppointmentTimesAdapter);

                break;
            //right page, aboutPage
            case 1:
                detail_description_textview = (TextView)view.findViewById(R.id.detail_description_textview);
                detail_description_title_textview = (TextView)view.findViewById(R.id.detail_description_title_textview);
                detail_business_diffrences_textview = (TextView)view.findViewById(R.id.detail_business_diffrences_textview);
                detail_learning_trade_textview = (TextView)view.findViewById(R.id.detail_learning_trade_textview);
                detail_why_love_textview = (TextView)view.findViewById(R.id.detail_why_love_textview);
                detail_business_diffrences_title_textview = (TextView)view.findViewById(R.id.detail_business_diffrences_title_textview);
                detail_learning_trade_title_textview = (TextView)view.findViewById(R.id.detail_learning_trade_title_textview);
                detail_why_love_title_textview = (TextView)view.findViewById(R.id.detail_why_love_title_textview);
                break;
        }
    }

    private void setting(View view, int page){
        switch(page){
            case 0:
                break;
            case 1:

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

                break;
        }
    }

    private void setClickListener(View view , int page){
        switch(page){
            case 0:
                break;
            case 1:

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

                break;
        }

    }









    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
//        if(position>=photo_urls.size()) return null;

        View view=null;

        if(position==0) {
            view = inflater.inflate(R.layout.detail_info_viewpager_book_item, null);

            //do something
            bookInformationPage(view);
        }else{ //position ==1
            view = inflater.inflate(R.layout.detail_info_viewpager_about_item, null);

            //do something
            aboutPage(view);
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
