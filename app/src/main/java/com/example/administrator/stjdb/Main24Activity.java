package com.example.administrator.stjdb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Main24Activity extends AppCompatActivity {

    Button huiyuan,chuizi,sanniangmu,jincha;
    TextView guize;
    String userid=null,friendid=null,ttt=null;
    int tag=0;
    String[] guangao={"咕噜咕噜锤","咕噜咕噜叉","咕噜咕噜三娘子管金叉","叉子叉你狗脑袋","锤子锤你三娘母"};








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        Intent intent = getIntent();
        userid = intent.getStringExtra("userid").trim();
        friendid = intent.getStringExtra("friendid").trim();
        ttt = intent.getStringExtra("tag").trim();

        //锤子、金叉、三娘母    1.2.3
        chuizi = (Button) this.findViewById(R.id.button8);
        sanniangmu = (Button) this.findViewById(R.id.button9);
        jincha = (Button) this.findViewById(R.id.button10);
        huiyuan = (Button) this.findViewById(R.id.button15);
        guize = (TextView) this.findViewById(R.id.textView12);


        //广告片
         final Handler handler = new Handler();
         Runnable runnable = new Runnable() {
            public void run () {
                if (tag<5){
                    guize.setText("\n"+guangao[tag]);
                    tag++;
                }else if (tag>=5){
                    tag=0;
                }
                handler.postDelayed(this,2000);
            }
        };
        handler.postDelayed(runnable,2000);


        huiyuan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main24Activity.this, Main214Activity.class);
                startActivity(intent);
            }
        });


        chuizi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ttt.equals("0")&&friendid != null && friendid.trim() != "") {
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("friendid", friendid.trim());
                    values.put("result1", "1");
                    http.Request("addchuquan", values);
                }
                else if (ttt.equals("0")){
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("result2", "1");
                    http2.Request("addchuquan2", values);
                }

            }
        });


        jincha.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ttt.equals("0")&&friendid != null && friendid.trim() != "") {
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("friendid", friendid.trim());
                    values.put("result1", "2");
                    http.Request("addchuquan", values);
                }
                else if (ttt.equals("0")){
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("result2", "2");
                    http2.Request("addchuquan2", values);
                }

            }
        });



        sanniangmu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ttt.equals("0")&&friendid != null && friendid.trim() != "") {
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("friendid", friendid.trim());
                    values.put("result1", "3");
                    http.Request("addchuquan", values);
                }
                else if (ttt.equals("0")){
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("myid", userid.trim());
                    values.put("result2", "3");
                    http2.Request("addchuquan2", values);
                }

            }
        });




    }


    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    Toast.makeText(Main24Activity.this, "约战成功！" + http.Result, Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    //intent.putExtra("userid",username.getText().toString().trim());
                    //startActivity(intent);
                    Main24Activity.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    Toast.makeText(Main24Activity.this, "约战失败！"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });



    WebService http2=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http2.Result != null) {
                if (http2.Result.equals("true")) {
                    Toast.makeText(Main24Activity.this, "挑战完成！" + http2.Result, Toast.LENGTH_SHORT).show();
                    Main24Activity.this.finish();

                } else {
                    Toast.makeText(Main24Activity.this, "挑战失败！"+http2.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

}
