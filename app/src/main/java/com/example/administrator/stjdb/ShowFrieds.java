package com.example.administrator.stjdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

public class ShowFrieds extends Activity {

    String userid;
    ListView listView;
    ArrayAdapter adapter;
    Boolean flag=false;
    TextView xuanzehaoyou;
    Button startgo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_frieds);



        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");

        listView=(ListView)this.findViewById(R.id.listView4);
        xuanzehaoyou=(TextView)this.findViewById(R.id.textView27);
        startgo=(Button)this.findViewById(R.id.button45);


        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);
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
                xuanzehaoyou.setText("\n" + o.toString());
                Toast.makeText(ShowFrieds.this, "你已选择对象：\n" + o.toString(), Toast.LENGTH_SHORT).show();
                flag = true;

            }
        });



        startgo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    Toast.makeText(ShowFrieds.this,"请选择一个对象！", Toast.LENGTH_SHORT).show();
                    // return;
                }
                else if (flag){
                    Intent intent = new Intent(ShowFrieds.this, SingSong.class);
                    String friendid=xuanzehaoyou.getText().toString().split("\n")[0];
                    intent.putExtra("userid",userid);
                    intent.putExtra("friendid",friendid.trim());
                    startActivity(intent);
                    ShowFrieds.this.finish();
                }

            }
        });


        //alertDialog.setTitle("              操作提示").setMessage("       发送告白信息，系统会对你的帐号信息进行保密，对方只能看到你发送的内容，其他的均看不到！如果TA对你有感觉，才会逐渐了解到你！所以，你没有失败，只有成功！Good Lucky！").setPositiveButton("确定", null).show();


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
                adapter =new ArrayAdapter(ShowFrieds.this,android.R.layout.simple_list_item_1,ls);
                listView.setAdapter(adapter);

            } else {
                Toast.makeText(ShowFrieds.this, "获取失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });

}
