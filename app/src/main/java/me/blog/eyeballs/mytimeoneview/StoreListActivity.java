package me.blog.eyeballs.mytimeoneview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.blog.eyeballs.mytimeoneview.stores_list.Adapter;

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

//        StringBuffer temp = null;
//        temp.append(datas.get(0).getName()+"\n" +
//                datas.get(0).getCity()+"\n"+
//                datas.get(0).getMin_price()+"\n"+
//                datas.get(0).getService_name());


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
