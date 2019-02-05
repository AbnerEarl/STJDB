package com.example.administrator.stjdb;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Main212Activity extends AppCompatActivity {

    EditText information;
    TextView tishi;
    Button fasong;
    String myuserid,frienduserid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main212);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        myuserid= intent.getStringExtra("myuserid");
        frienduserid=intent.getStringExtra("frienduserid");
        information=(EditText)this.findViewById(R.id.editText8);
        tishi=(TextView)this.findViewById(R.id.textView19);
        fasong=(Button)this.findViewById(R.id.button30);
        fasong.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!yanzheng()){
                    Toast.makeText(Main212Activity.this, "请书写告白话语！", Toast.LENGTH_SHORT).show();
                    // return;
                }
                else if (yanzheng()){

                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myuserid", myuserid.trim());
                    values.put("frienduserid",frienduserid.trim());
                    values.put("information",information.getText().toString().trim());
                    http.Request("addinformation", values);
                }
            }
        });

    }

    public Boolean yanzheng(){
        if (information.getText().toString().trim().length()<2){
            tishi.setText("表白内容不合理！");
            return false;
        }else {
        return true;
        }
    }


    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    Toast.makeText(Main212Activity.this, "发送成功" + http.Result, Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    //intent.putExtra("userid",username.getText().toString().trim());
                    //startActivity(intent);
                    Main212Activity.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    Toast.makeText(Main212Activity.this, "发送失败"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

}
