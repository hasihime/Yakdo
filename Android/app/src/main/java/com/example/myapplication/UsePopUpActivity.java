package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsePopUpActivity extends Activity {

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfYYYYMM = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat sdfDHM = new SimpleDateFormat("dd일 HH:mm" );

    long YYYYMM = Long.parseLong(sdfYYYYMM.format(date));
    String DHM = sdfDHM.format(date);

    TextView titleText;
    TextView typeText;
    EditText countUse;
    SquareImageView typeImage;
    private DbOpenHelper mDbOpenHelper;
    private long key;
    private String name;
    private long stock;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.use_popup_activity);

        //UI 객체생성
        titleText = (TextView)findViewById(R.id.use_drug_name);
        typeText = (TextView)findViewById(R.id.use_type_Text);
        typeImage = (SquareImageView)findViewById(R.id.use_image);
        countUse = (EditText)findViewById(R.id.use_drug_count);

        //데이터 가져오기
        Intent intent = getIntent();
        this.key = Long.parseLong(intent.getStringExtra("key_id"));
        this.name = intent.getStringExtra("name");
        this.stock = Long.parseLong(intent.getStringExtra("stock"));
        this.type = intent.getStringExtra("type");
        this.typeImage.setImageURI(Uri.parse("android.resource://com.example.myapplication/drawable/" + intent.getStringExtra("type").split(" ")[1]));
        titleText.setText(this.name);
        typeText.setText("종류 : " + this.type.split(" ")[0] + "\n");
        countUse.setText("1");
        countUse.setFilters(new InputFilter[]{ new InputFilterMinMax("1", this.stock+"")});
    }

    //확인 버튼 클릭
    public void onUse(View view){
        long usingCount = Long.parseLong(countUse.getText().toString());
        //데이터 전달하기
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        if(this.stock > usingCount) mDbOpenHelper.updateColumn0(this.key, this.name, this.stock - usingCount, this.type);
        else mDbOpenHelper.deleteColumn(this.key);
        mDbOpenHelper.insertColumn1(DHM + " "
                        + this.type.split(" ")[0] + "형의 약 "
                        + name + "을(를) "
                        + usingCount + "개 사용."
                , YYYYMM);
        mDbOpenHelper.close();
        Intent intent = new Intent();
        intent.putExtra("result", true);
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    public void onGive(View view){
        if(countUse.getText().toString().trim().equals("")) return;
        long usingCount = Long.parseLong(countUse.getText().toString());
        //데이터 전달하기
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        if(this.stock > usingCount) mDbOpenHelper.updateColumn0(this.key, this.name, this.stock - usingCount, this.type);
        else mDbOpenHelper.deleteColumn(this.key);
        mDbOpenHelper.insertColumn1(DHM + " "
                        + this.type.split(" ")[0] + "형의 약 "
                        + name + "을(를) "
                        + usingCount + "개 줌."
                , YYYYMM);
        mDbOpenHelper.close();
        Intent intent = new Intent();
        intent.putExtra("result", true);
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    public void onThrow(View view){
        long usingCount = Long.parseLong(countUse.getText().toString());
        //데이터 전달하기
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        if(this.stock > usingCount) mDbOpenHelper.updateColumn0(this.key, this.name, this.stock - usingCount, this.type);
        else mDbOpenHelper.deleteColumn(this.key);
        mDbOpenHelper.insertColumn1(DHM + " "
                        + this.type.split(" ")[0] + "형의 약 "
                        + name + "을(를) "
                        + usingCount + "개 버림."
                , YYYYMM);
        mDbOpenHelper.close();
        Intent intent = new Intent();
        intent.putExtra("result", true);
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}
