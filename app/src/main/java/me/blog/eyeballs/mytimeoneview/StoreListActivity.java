package me.blog.eyeballs.mytimeoneview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import me.blog.eyeballs.mytimeoneview.stores_list.Adapter;

public class StoreListActivity extends AppCompatActivity implements DataAccessible {

    Adapter storesListAdapter;
    ListView storesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        setList();
    }

    private void setList() {
        storesListAdapter = new Adapter(this);
        storesList = (ListView) findViewById(R.id.store_listview);
        storesList.setAdapter(storesListAdapter);
    }
}
