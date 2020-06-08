package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {
    private DbOpenHelper mDbOpenHelper;
    private ArrayList logList;
    TextView textView;
    ListView list;
    ListAdapter adapter;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");

    long YYYY = Long.parseLong(sdfYear.format(date));
    long MM = Long.parseLong(sdfMonth.format(date));
    long tY = YYYY;
    long tM = MM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        list = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
//        testdelete();
//        testinsert();
        showList();
    }

    public void testdelete(){
        mDbOpenHelper.deleteAllColumns1();
    }

    public void testinsert(){
        for (int i=0; i<10; i++ ) {
            mDbOpenHelper.insertColumn1("test"+i, YYYY*100 + MM - (i / 2));
        }
    }

    public void showList(){
        textView.setText(tY + "년 " + tM + "월");
        Cursor c = mDbOpenHelper.selectYMLogs(tY * 100 + tM);

        logList = new ArrayList();
        if(c != null){
            if(c.moveToFirst()){
                do {
                    //테이블에서 두개의 컬럼값을 가져와서
                    String Plaintext = c.getString(c.getColumnIndex("plaintext"));

                    //HashMap에 넣습니다.
                    HashMap<String,String> logs = new HashMap<String,String>();

                    logs.put("plaintext" ,Plaintext);

                    //ArrayList에 추가합니다..
                    logList.add(logs);

                } while (c.moveToNext());
            }
        }
        mDbOpenHelper.close();

        adapter = new SimpleAdapter(
                this, logList, R.layout.log_list_item,
                new String[]{"plaintext"},
                new int[]{ R.id.plaintext}
        );


        //화면에 보여주기 위해 Listview에 연결합니다.
        list.setAdapter(adapter);
    }

    public void onClick(View view) {
        finish();
    }

    public void goBefore(View view) {
        if(--tM == 0) {
            --tY;
            tM = 12;
        }
        mDbOpenHelper.open();
        showList();
    }

    public void goAfter(View view){
        if(tY == YYYY && tM == MM) return;
        else if(++tM > 12) {
            ++tY;
            tM = 1;
        }
        mDbOpenHelper.open();
        showList();
    }
}
