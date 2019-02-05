package com.example.administrator.stjdb;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Main213Activity extends AppCompatActivity {

    TextView xiaoxi;
    Button yiyue,huiyuan,huifu;
    String userid=null,xinxi=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main213);


        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");
        xinxi=intent.getStringExtra("xinxi");

        xiaoxi=(TextView)this.findViewById(R.id.textView18);
        yiyue=(Button)this.findViewById(R.id.button33);
        huifu=(Button)this.findViewById(R.id.button50) ;
        huiyuan=(Button)this.findViewById(R.id.button32);

        if (xinxi!=null) {
            xiaoxi.setText("TA对你说：\n\n         " + xinxi.trim());
        }
        else {

            huifu.setVisibility(huifu.INVISIBLE);

            yiyue.setText("返回主页");
        }

        yiyue.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (userid == null || xinxi == null) {
                    Main213Activity.this.finish();
                } else {
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myuserid", userid.trim());
                    http.Request("hadread", values);

                }
            }
        });


        huiyuan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main213Activity.this,Main214Activity.class);
                //intent.putExtra("xinxi",editText.getText().toString().trim()+","+editText2.getText().toString().trim());
                startActivity(intent);

            }
        });



        huifu.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main213Activity.this,MessageReturn.class);
                intent.putExtra("userid", userid);
                //intent.putExtra("xinxi",editText.getText().toString().trim()+","+editText2.getText().toString().trim());
                startActivity(intent);

            }
        });





    }


    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    Toast.makeText(Main213Activity.this, "已阅成功" + http.Result, Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    //intent.putExtra("userid",username.getText().toString().trim());
                    //startActivity(intent);
                    Main213Activity.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    Toast.makeText(Main213Activity.this, "已阅失败"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });




}
