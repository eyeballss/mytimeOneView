package me.blog.eyeballs.mytimeoneview.detail_lists;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import me.blog.eyeballs.mytimeoneview.R;
import me.blog.eyeballs.mytimeoneview.ShowWebImage;

/**
 * Created by eye on 16. 11. 6.
 */
public class ViewpageAdapter extends PagerAdapter {
    ArrayList<String> photo_urls;

    LayoutInflater inflater;
    public ViewpageAdapter(LayoutInflater inflater, ArrayList<String> photo_urls) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.photo_urls = photo_urls;
    }

    public ViewpageAdapter(LayoutInflater inflater,String photo_url) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.photo_urls = new ArrayList<String>();
        this.photo_urls.add(photo_url);
    }

    @Override
    public int getCount() {
        return photo_urls.size();
    }

    //ViewPager가 현재 보여질 Item(View객체)를 생성할 필요가 있는 때 자동으로 호출
    //쉽게 말해, 스크롤을 통해 현재 보여져야 하는 View를 만들어냄.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : ViewPager가 보여줄 View의 위치(가장 처음부터 0,1,2,3...)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
//        if(position>=photo_urls.size()) return null;

        View view=null;

        //새로운 View 객체를 Layoutinflater를 이용해서 생성
        //만들어질 View의 설계는 res폴더>>layout폴더>>viewpater_childview.xml 레이아웃 파일 사용
        view= inflater.inflate(R.layout.detail_viewpager_item, null);

        //만들어진 View안에 있는 ImageView 객체 참조
        //위에서 inflated 되어 만들어진 view로부터 findViewById()를 해야 하는 것에 주의.
        ImageView img= (ImageView)view.findViewById(R.id.detail_viewpager_images);

        //ImageView에 현재 position 번째에 해당하는 이미지를 보여주기 위한 작업
        //현재 position에 해당하는 이미지를 setting
//        for(int i=0; i<photo_urls.size(); i++){
//
//        }
        new ShowWebImage().setImageView(img).execute(photo_urls.get(position));


        //ViewPager에 만들어 낸 View 추가
        container.addView(view);

        //Image가 세팅된 View를 리턴
        return view;
    }

    //화면에 보이지 않은 View는파쾨를 해서 메모리를 관리함.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
    //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub

        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View)object);

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
