package me.blog.eyeballs.mytimeoneview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import me.blog.eyeballs.mytimeoneview.list.Adapter;

public class StoreListActivity extends AppCompatActivity {
    static ArrayList<Data> datas;
    static Adapter storesListAdapter;
    ListView storesList;

    //Gson gson = new GsonBuilder().create();
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        jsonToJava();
        setList();

        Bitmap a = show1("https://s3.amazonaws.com/mytime_prod/attachments/449/slate_black/Fotolia_29883114_Subscription_Monthly_XXL.png?1391219770");
        ImageView b = (ImageView)findViewById(R.id.testImageView);
        b.setImageBitmap(a);

    }

    Bitmap bitmap;
    Thread thread = null;
    public Bitmap show1(final String u) {
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














    private void setList() {
        if(storesListAdapter != null) return;
        else storesListAdapter = new Adapter(this, datas);

        storesList= (ListView)findViewById(R.id.store_listview);
        storesList.setAdapter(storesListAdapter);
    }

    protected void jsonToJava(){
        if( datas != null) return;
        else datas = new ArrayList<Data>();

        try {
            JSONArray jarray = new JSONArray(RawData.rawData);   // JSONArray 생성
            Data tempData;
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출

                tempData = Data.generateData(jObject);
                datas.add(tempData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
