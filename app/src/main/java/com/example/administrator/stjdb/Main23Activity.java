package com.example.administrator.stjdb;

import android.app.ListActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

//AppCompatActivity,ListActivity
public class Main23Activity extends AppCompatActivity  {

    Button begin;
    ListView listView;
    Boolean flag=false;
    TextView name,number,xuanzehaoyou;
    String userid;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");



        xuanzehaoyou=(TextView)this.findViewById(R.id.textView9);
        xuanzehaoyou.setText(null);
        //number=(TextView)this.findViewById(R.id.textView10);
        listView=(ListView)this.findViewById(R.id.listView);
        begin=(Button)this.findViewById(R.id.button7);



        Map<String, String> values = new HashMap<String, String>();
        values.put("myuserid", userid.trim());
        //            values.put("frienduserid",friendid.trim());
        http1.Request("chaxunfriends", values);


        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = listView.getItemAtPosition(position);
               /* String st = "sdcard/";
                File f = new File(st+o.toString());*/
                xuanzehaoyou.setText(o.toString());
                Toast.makeText(Main23Activity.this, "你已选择逗友：\n"+o.toString(), Toast.LENGTH_SHORT).show();
                flag=true;

            }
        });



        begin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    Toast.makeText(Main23Activity.this,"请选择一个逗友", Toast.LENGTH_SHORT).show();
                    // return;
                }
                else if (flag){
                    Intent intent = new Intent(Main23Activity.this, Main24Activity.class);
                    String friendid=xuanzehaoyou.getText().toString().split("\n")[0];
                    intent.putExtra("userid",userid);
                    intent.putExtra("friendid",friendid.trim());
                    intent.putExtra("tag","");
                    startActivity(intent);
                }
            }
        });





    }


    WebService http1=new WebService(new RequestFunc() {
        @Override
        public void Func() {
                if (http1.Result!= null) {

                    List<String> ls = new ArrayList<String>();
                    String []tem=http1.Result.split(":");
                    for(int i=0;i+1<tem.length;i+=2){
                        ls.add(tem[i]+"\n"+tem[i+1]);
                    }
                    adapter =new ArrayAdapter(Main23Activity.this,android.R.layout.simple_list_item_1,ls);
                    listView.setAdapter(adapter);

                } else {
                    Toast.makeText(Main23Activity.this, "查找失败"+http1.Result, Toast.LENGTH_SHORT).show();
                }

        }
    });




}
