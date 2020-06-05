package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.ArrayList;

public class MypharmacyActivity extends AppCompatActivity {

    private DbOpenHelper mDbOpenHelper;
    private ArrayList<HashMap<String, String>> drugList;
    TableLayout table;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypharmacy);

        table = (TableLayout) findViewById(R.id.tableView);
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
        table.removeAllViewsInLayout();
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        for(int i = 0; i < drugList.size(); i++){
            if(i != 0 && i % 3 == 0){
                table.addView(tableRow);
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            }
            ConstraintLayout cl = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.list_item, null);
            cl.setId(i);
            TextView name = (TextView) cl.findViewById(R.id.name);
            name.setText(drugList.get(i).get("name"));
            TextView type = (TextView) cl.findViewById(R.id.type);
            type.setText(drugList.get(i).get("type"));
            TextView stock = (TextView) cl.findViewById(R.id.stock);
            stock.setText("x" + drugList.get(i).get("stock"));
            cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TextView 클릭될 시 할 코드작성
                    Intent intent = new Intent(getApplicationContext(), UsePopUpActivity.class);
                    intent.putExtra("key_id", drugList.get(v.getId()).get("key_id"));
                    intent.putExtra("name", drugList.get(v.getId()).get("name"));
                    intent.putExtra("stock", drugList.get(v.getId()).get("stock"));
                    intent.putExtra("type", drugList.get(v.getId()).get("type"));
                    startActivityForResult(intent, 1);
                }
            });
            tableRow.addView(cl);
        }
        table.addView(tableRow);
        tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 3; i++){
            TextView tmpView = new TextView(this);
            tableRow.addView(tmpView);
        }
        table.addView(tableRow);
        ImageButton regButton = (ImageButton) findViewById(R.id.reg);
        regButton.bringToFront();
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
