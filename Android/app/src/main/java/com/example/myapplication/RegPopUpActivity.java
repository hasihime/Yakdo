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

public class RegPopUpActivity extends Activity {

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfYYYYMM = new SimpleDateFormat("yyyyMM");
    SimpleDateFormat sdfDHM = new SimpleDateFormat("dd일 HH:mm" );

    long YYYYMM = Long.parseLong(sdfYYYYMM.format(date));
    String DHM = sdfDHM.format(date);

    EditText nameText;
    EditText countNum;
    TextView typeText;
    SquareImageView typeImage;
    private DbOpenHelper mDbOpenHelper;
    private final String[] types = {"알약", "캡슐", "밴드", "가루약", "물파스", "파스", "연고", "스프레이", "안약", "기타"};
    private final String[] typesDB = {"알약 pill", "캡슐 capsule", "밴드 band", "가루약 flour", "물파스 liquid_pas", "파스 patch", "연고 ointment", "스프레이 spray", "안약 drop", "기타 etc"};
    private int type_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.reg_popup_activity);

        //UI 객체생성
        nameText = (EditText)findViewById(R.id.reg_drug_name);
        countNum = (EditText)findViewById(R.id.reg_drug_count);
        countNum.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "1000")});
        typeText = (TextView)findViewById(R.id.reg_drug_type);
        typeImage = (SquareImageView)findViewById(R.id.reg_image);

        //데이터 바인딩
        countNum.setText("1");
        typeText.setText(types[type_index]);
        typeImage.setImageURI(Uri.parse("android.resource://com.example.myapplication/drawable/" + typesDB[type_index].split(" ")[1]));
    }

    //등록 버튼 클릭
    public void submit(View view){
        if(nameText.getText().toString().trim().equals("") || countNum.getText().toString().trim().equals("")) return;
        //데이터 전달하기
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.insertColumn0(nameText.getText().toString(), Long.parseLong(countNum.getText().toString()), typesDB[type_index]);
        mDbOpenHelper.insertColumn1(DHM + " "
                + types[type_index] + "형의 약 "
                + nameText.getText().toString() + "을(를) "
                + countNum.getText().toString() + "개 구입."
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

    public void goPre(View view) {
        if(--type_index < 0) {
            type_index += types.length;
        }
        typeText.setText(types[type_index]);
        typeImage.setImageURI(Uri.parse("android.resource://com.example.myapplication/drawable/" + typesDB[type_index].split(" ")[1]));
    }

    public void goNext(View view) {
        if(++type_index == types.length) {
            type_index = 0;
        }
        typeText.setText(types[type_index]);
        typeImage.setImageURI(Uri.parse("android.resource://com.example.myapplication/drawable/" + typesDB[type_index].split(" ")[1]));
    }

}