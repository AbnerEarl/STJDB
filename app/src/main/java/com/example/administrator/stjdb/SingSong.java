package com.example.administrator.stjdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SingSong extends Activity {

    String myuserid,frienduserid;
    Button diange;
    EditText geming,geshou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_song);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        myuserid= intent.getStringExtra("myuserid");
        frienduserid=intent.getStringExtra("frienduserid");

        diange=(Button)this.findViewById(R.id.button44);
        geming=(EditText)this.findViewById(R.id.editText10) ;
        geshou=(EditText)this.findViewById(R.id.editText11) ;


        diange.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (geming.getText().toString().trim().length()>1){
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myuserid", myuserid.trim());
                    values.put("frienduserid",frienduserid.trim());
                    values.put("song",geming.getText().toString().trim());
                    values.put("author",geshou.getText().toString().trim());
                    http.Request("diansong", values);
                }else {
                    Toast.makeText(SingSong.this, "请输入歌名！", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    Toast.makeText(SingSong.this, "点歌成功" + http.Result, Toast.LENGTH_SHORT).show();
                    SingSong.this.finish();
                } else {
                    Toast.makeText(SingSong.this, "点歌失败"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }

        }
    });






}
