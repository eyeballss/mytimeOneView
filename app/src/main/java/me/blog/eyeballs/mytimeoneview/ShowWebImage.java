package me.blog.eyeballs.mytimeoneview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eye on 16. 10. 24.
 */
public class ShowWebImage {
    Thread thread = null;
    Handler handler = new Handler();

    public void show(final ImageView v, final String u) {
        stop();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final ImageView view = v;
                URL url = null;
                try {
                    url = new URL(u);
                    InputStream inputStream = url.openStream();

                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(bitmap);
                        }
                    });
                    view.setImageBitmap(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    Bitmap bitmap;
    public Bitmap show1(final String u) {
        stop();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(u);
                    InputStream inputStream = url.openStream();

                    bitmap = BitmapFactory.decodeStream(inputStream);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    return bitmap;
    }

    public static Bitmap getImage(String strUrl){
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        Bitmap bm = null;


        try {
            url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();

            is = conn.getInputStream();
            bis = new BufferedInputStream(is);

            bm = BitmapFactory.decodeStream(bis);

        } catch (Exception e) {
            //throw e;
            return null;
        }finally{
            try{
                if(is != null) is.close();
                if(bis != null) bis.close();
            }catch(Exception e){}
        }

        return bm;
    }

    void stop() {
        if (thread != null && thread.isAlive()) { //if the thread is alive still
            thread.interrupt(); //stop thread
            thread.stop();
        }
    }
}
