package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);

        permissionCheck();
    }

    private void initWebView(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        // 자바스크립트 사용을 허용한다.
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }
                // tel일경우 아래와 같이 처리해준다.
                else if (url.startsWith("tel:")) {
                    Intent tel = new Intent(Intent.ACTION_DIAL, Uri.parse(url.replace('+',' ').trim()));
                    startActivity(tel);
                    return true;
                }
                return true;
            }
        }); // 새로운 창을 띄우지 않고 내부에서 웹뷰를 실행시킨다.
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }
        });
        webView.loadUrl("https://k02a4051.p.ssafy.io");
    }

    //폰의 뒤로가기 버튼의 동작 입력
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MypharmacyActivity.class);
        startActivity(intent);
    }

    private void permissionCheck(){
        initWebView();
        String temp = "";
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, 0);
//        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Manifest.permission.ACCESS_FINE_LOCATION 접근 승낙 상태 일때
            LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            if(!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                alertCheckGPS();
            }
        } else{ //Manifest.permission.ACCESS_FINE_LOCATION 접근 거절 상태 일때
            // 사용자에게 접근권한 설정을 요구하는 다이얼로그를 띄운다.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            if(!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                alertCheckGPS();
            }
            initWebView();
        }
    }

    /** GPS 설정 유무 확인 **/
    private void alertCheckGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS 기능이 꺼져있습니다 GPS기능을 켜시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("GPS 설정하기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveConfigGPS();
                            }
                        })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    /** GPS 설정화면으로 이동 **/
    private void moveConfigGPS() {
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        gpsOptionsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(gpsOptionsIntent);
    }

}