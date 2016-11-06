package me.blog.eyeballs.mytimeoneview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.blog.eyeballs.mytimeoneview.stores_list.Adapter;

public class StoreListActivity extends AppCompatActivity implements DataAccessible {

    //검색은 search view를 이용하면 되겠군.
//    static Adapter storesListAdapter;
    Adapter storesListAdapter;
    ListView storesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        jsonToJava();
        setList();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        storesListAdapter.notifyDataSetChanged();
//    }

    private void setList() {
        storesListAdapter = new Adapter(this);
        storesList = (ListView) findViewById(R.id.store_listview);
        storesList.setAdapter(storesListAdapter);

//        if(storesListAdapter != null) {
//            storesListAdapter.clear();
//            return;
//        }
//        else {
//            storesListAdapter = new Adapter(this);
//            storesList = (ListView) findViewById(R.id.store_listview);
//            storesList.setAdapter(storesListAdapter);
//        }
    }

    protected void jsonToJava(){

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
