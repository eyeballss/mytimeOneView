package me.blog.eyeballs.mytimeoneview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;

/**
 * Created by eye on 16. 10. 24.
 */

public class ShowWebImage extends AsyncTask<String, Void, Bitmap> {
    private int flag = -1;
    ImageView bmImage;
    LinearLayout llBackground;

    public ShowWebImage setImageView(ImageView imageView) {
        flag=0;
        this.bmImage = imageView;
        return this;
    }

    public ShowWebImage setImageView(LinearLayout llBackground) {
        flag=1;
        this.llBackground = llBackground;
        return this;
    }

    protected Bitmap doInBackground(String... urls) {

        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    //flag =0 ; ImageView
    //flag =1 ; LinearLayout
    protected void onPostExecute(Bitmap result) {
        if(flag ==0)
            bmImage.setImageBitmap(result);
        else if(flag ==1) {
            BitmapDrawable ob = new BitmapDrawable(result);
            llBackground.setBackground(ob);
        }
    }
}