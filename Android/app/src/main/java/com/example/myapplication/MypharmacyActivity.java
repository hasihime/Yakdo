package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.LinkedList;

public class MypharmacyActivity extends AppCompatActivity {

    private DbOpenHelper mDbOpenHelper;
    private LinkedList drugList;
    ListView list;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypharmacy);

        list = (ListView) findViewById(R.id.listView);
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        testdelete();
        testinsert();
        showList();
    }

    public void testdelete(){
        mDbOpenHelper.deleteAllColumns0();
    }

    public void testinsert(){
        for (int i=0; i<5; i++ ) {
            mDbOpenHelper.insertColumn0("test"+i, 10, "알약");
        }
    }

    public void showList(){
        Cursor c = mDbOpenHelper.selectDrugs();
        drugList = new LinkedList();
        if(c != null){
            if(c.moveToFirst()){
                do {

                    //테이블에서 두개의 컬럼값을 가져와서
                    String Name = c.getString(c.getColumnIndex("name"));
                    String Stock = c.getString(c.getColumnIndex("stock"));
                    String Type = c.getString(c.getColumnIndex("type"));

                    //HashMap에 넣습니다.
                    HashMap<String,String> drugs = new HashMap<String,String>();

                    drugs.put("name" ,Name);
                    drugs.put("stock" ,Stock);
                    drugs.put("type", Type);

                    //ArrayList에 추가합니다..
                    drugList.add(drugs);

                } while (c.moveToNext());
            }
        }
        mDbOpenHelper.close();

        adapter = new SimpleAdapter(
                this, drugList, R.layout.list_item,
                new String[]{"name" ,"stock" ,"type"},
                new int[]{ R.id.name, R.id.stock, R.id.type}
        );


        //화면에 보여주기 위해 Listview에 연결합니다.
        list.setAdapter(adapter);
    }

    public void goMain(View view) {
        finish();
    }
    public void goLog(View view) {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}
