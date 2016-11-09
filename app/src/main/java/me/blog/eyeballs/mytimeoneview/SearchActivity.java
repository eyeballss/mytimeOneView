package me.blog.eyeballs.mytimeoneview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements DataAccessible  {

    SearchView search_name, search_city;
    private boolean storeNameChecker, cityNameChecker;
    private ArrayList<Integer> resultBySearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
        jsonToJava();
        setQueryTextListener();
    }

    private void init() {
        search_name = (SearchView)findViewById(R.id.search_name_searchview);
        search_city = (SearchView)findViewById(R.id.search_city_searchview);
        storeNameChecker=false;
        cityNameChecker=false;
    }



    private void setQueryTextListener() {
        search_name.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //문자열 입력을 완료했을 때 문자열 반환
                searchByQuery();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //문자열이 변할 때마다 바로바로 문자열 반환
                if(newText.trim().length()==0){
                    storeNameChecker=false;
                }else {
                    storeNameChecker=true;
                }
                return false;
            }
        });

        search_city.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchByQuery();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.trim().length()==0){
                    cityNameChecker=false;
                }else {
                    cityNameChecker=true;
                }
                return false;
            }
        });
    }

    private void searchByQuery(){

        if(search_name.getQuery().toString().equals("My Time")){
            Intent intent = new Intent(this, SecretPage.class);
            startActivity(intent);
            return;
        }

        String storeNameQuery = search_name.getQuery().toString().trim().replaceAll(" ","").toUpperCase();
        String cityNameQuery = search_city.getQuery().toString().trim().replaceAll(" ","").toUpperCase();

        resultBySearchList = (ArrayList<Integer>) searchByStoreName(storeNameQuery);
        resultBySearchList = (ArrayList<Integer>) searchByCityName(cityNameQuery, resultBySearchList);

        if(resultBySearchList.size()==0){
            Toast.makeText(getApplicationContext(), "No Result", Toast.LENGTH_SHORT).show();
        }else{
            for(int i=0; i<resultBySearchList.size(); i++){
                searchResultList.add(resultBySearchList.get(i));
            }

            showStoreListActivity();


        }
    }

    private void showStoreListActivity(){
        Intent intent = new Intent(this, StoreListActivity.class);
        startActivity(intent);
        if(resultBySearchList!=null)
            resultBySearchList.clear();
    }


    public void search_bottom_button_onclick(View v){

        switch (v.getId()){
            case R.id.search_button:
                searchByQuery();
            break;
        }
    }



    private List searchByCityName(String query, ArrayList<Integer> list){

        if(!cityNameChecker){
            return list;
        }else{
            ArrayList<Integer> gatherer = new ArrayList<Integer>();
            String temp;
            for(int i=0; i<list.size(); i++){
                temp = datas.get(list.get(i)).getCity().trim().replaceAll(" ","").toUpperCase();
                if(temp.contains(query)){
                    gatherer.add(list.get(i));
                }//if
            }//for
            return gatherer;
        }//else
    }

    private List searchByStoreName(String query){
        ArrayList<Integer> gatherer = new ArrayList<Integer>();
        if(!storeNameChecker){
            for(int i=0; i<datas.size(); i++){
                gatherer.add(i);
            }

        }else{
            String temp;
            for(int i=0; i<datas.size(); i++){
                temp = datas.get(i).getName().trim().replaceAll(" ","").toUpperCase();
                if(temp.contains(query)){
                    gatherer.add(i);
                }//if
            }//for
        }//else
        return gatherer;
    }


    @Override
    protected void onResume() {
        super.onResume();
        searchResultList.clear();
    }

    protected void jsonToJava(){
        datas.clear();
        try {
            JSONArray jarray = new JSONArray(RawData.rawData);   // JSONArray
            Data tempData;
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject

                tempData = Data.generateData(jObject);
                datas.add(tempData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
