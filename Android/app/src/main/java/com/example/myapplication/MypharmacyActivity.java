package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.ArrayList;

public class MypharmacyActivity extends AppCompatActivity {

    private DbOpenHelper mDbOpenHelper;
    private ArrayList<HashMap<String, String>> drugList;
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
//        testdelete();
//        testinsert();
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
        drugList = new ArrayList();
        if(c != null){
            if(c.moveToFirst()){
                do {

                    //테이블에서 네개의 컬럼값을 가져와서
                    String KeyId = c.getString(0);
                    String Name = c.getString(c.getColumnIndex("name"));
                    String Stock = c.getString(c.getColumnIndex("stock"));
                    String Type = c.getString(c.getColumnIndex("type"));

                    //HashMap에 넣습니다.
                    HashMap<String,String> drugs = new HashMap<String,String>();

                    drugs.put("key_id" ,KeyId);
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
                new String[]{"key_id", "name" ,"stock" ,"type"},
                new int[]{R.id.key_id, R.id.name, R.id.stock, R.id.type}
        );


        //화면에 보여주기 위해 Listview에 연결합니다.
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UsePopUpActivity.class);
                intent.putExtra("key_id", drugList.get(position).get("key_id"));
                intent.putExtra("name", drugList.get(position).get("name"));
                intent.putExtra("stock", drugList.get(position).get("stock"));
                intent.putExtra("type", drugList.get(position).get("type"));
                startActivityForResult(intent, 1);
            }
        });
    }

    public void goMain(View view) {
        finish();
    }
    public void goLog(View view) {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }

    public void goReg(View view){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, RegPopUpActivity.class);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                boolean result = data.getBooleanExtra("result", false);
                if(result) {
                    mDbOpenHelper.open();
                    showList();
                }
            }
        }
    }

}
