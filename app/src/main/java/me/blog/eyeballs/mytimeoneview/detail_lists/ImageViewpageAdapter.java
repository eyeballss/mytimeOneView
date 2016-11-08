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
public class ImageViewpageAdapter extends PagerAdapter {
    ArrayList<String> photo_urls;

    LayoutInflater inflater;
    public ImageViewpageAdapter(LayoutInflater inflater, ArrayList<String> photo_urls) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.photo_urls = photo_urls;
    }

    public ImageViewpageAdapter(LayoutInflater inflater, String photo_url) {
        // TODO Auto-generated constructor stub

        this.inflater=inflater;
        this.photo_urls = new ArrayList<String>();
        this.photo_urls.add(photo_url);
    }

    @Override
    public int getCount() {
        return photo_urls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        View view=null;

        view= inflater.inflate(R.layout.detail_image_viewpager_item, null);

        ImageView img= (ImageView)view.findViewById(R.id.detail_viewpager_images);

        new ShowWebImage().setImageView(img).execute(photo_urls.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View)object);

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
