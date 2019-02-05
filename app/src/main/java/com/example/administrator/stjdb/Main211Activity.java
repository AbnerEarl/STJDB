package com.example.administrator.stjdb;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main211Activity extends AppCompatActivity {
    String userid;
    ListView listView;
    ArrayAdapter adapter;
    Boolean flag=false;
    TextView xuanzehaoyou;
    Button startgo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main211);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");

        listView=(ListView)this.findViewById(R.id.listView3);
        xuanzehaoyou=(TextView)this.findViewById(R.id.textView16);
        startgo=(Button)this.findViewById(R.id.button29);





        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);
        Map<String, String> values = new HashMap<String, String>();
        values.put("mynumber", userid.trim());
        //            values.put("frienduserid",friendid.trim());
        http1.Request("gaobaiduixiang", values);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = listView.getItemAtPosition(position);
               /* String st = "sdcard/";
                File f = new File(st+o.toString());*/
                xuanzehaoyou.setText("\n" + o.toString());
                Toast.makeText(Main211Activity.this, "你已选择心仪对象：\n" + o.toString(), Toast.LENGTH_SHORT).show();
                flag = true;

            }
        });



        startgo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    Toast.makeText(Main211Activity.this, "请选择一个心仪对象！", Toast.LENGTH_SHORT).show();
                    // return;
                } else if (flag) {
                    String friendid = xuanzehaoyou.getText().toString().split("\n")[1];
                    Intent intent = new Intent(Main211Activity.this, Main212Activity.class);
                    intent.putExtra("myuserid", userid.trim());
                    intent.putExtra("frienduserid", friendid.trim());
                    startActivity(intent);
                }

            }
        });


        alertDialog.setTitle("              操作提示").setMessage("       发送告白信息，系统会对你的帐号信息进行保密，对方只能看到你发送的内容，其他信息均看不到！如果TA对你有感觉，会逐渐了解到你，当到了一定的了解程度，本系统会适时提供你们相互认识的机会！").setPositiveButton("确定", null).show();


    }


    WebService http1=new WebService(new RequestFunc() {
        @Override
        public void Func() {

            if (http1.Result!= null) {
                List<String> ls = new ArrayList<String>();
                String []tem=http1.Result.toString().split(":");
                for(int i=0;i+1<tem.length;i+=2){
                    ls.add(tem[i]+"\n"+tem[i+1]);
                }
                adapter =new ArrayAdapter(Main211Activity.this,android.R.layout.simple_list_item_1,ls);
                listView.setAdapter(adapter);

            } else {
                Toast.makeText(Main211Activity.this, "获取失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });


}
