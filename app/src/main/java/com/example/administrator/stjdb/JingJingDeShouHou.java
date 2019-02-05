package com.example.administrator.stjdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class JingJingDeShouHou extends Activity {

    String userid,xinxi=null;
    Button diange,singsong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_jing_de_shou_hou);

        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");

        diange=(Button)this.findViewById(R.id.button42);
        singsong=(Button)this.findViewById(R.id.button43);



        diange.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(JingJingDeShouHou.this,ShowFrieds.class);
                intent1.putExtra("userid",userid);
                startActivity(intent1);
            }
        });


        singsong.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (xinxi!=null){
                    Intent intent = new Intent(JingJingDeShouHou.this, ReceiveSong.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("xinxi", xinxi);
                    startActivity(intent);
                    xinxi = null;
                    singsong.setText("收到礼歌");
                }
                else {


                    Intent intent1 = new Intent(JingJingDeShouHou.this, ReceiveSong.class);
                    intent1.putExtra("userid", userid);
                    startActivity(intent1);
                }
            }
        });








        //实时检测歌单信息
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run () {

                //信息
                Map<String, String> values = new HashMap<String, String>();
                values.put("userid", userid.trim());
                //values.put("frienduserid",friendid.trim());
                http1.Request("chaxungedan", values);

                if (xinxi!=null){

                    //@android:color/holo_blue_light
                    singsong.setText("收到礼歌\n    +1");
                    //xiaoxi.setBackgroundResource(R.color.colorPrimary);
                }else {
                    singsong.setText("收到礼歌");
                }


                handler.postDelayed(this,2000);
            }
        };
        handler.postDelayed(runnable, 3000);


    }



    WebService http1=new WebService(new RequestFunc() {
        @Override
        public void Func() {

            if (http1.Result!= null&&http1.Result.toString().trim().split(":")[0].trim().equals("true")) {
                xinxi=http1.Result.toString().trim().split(":")[1]+":"+http1.Result.toString().trim().split(":")[2]+":"+http1.Result.toString().trim().split(":")[3];
            } else {
                xinxi=null;
                //Toast.makeText(Main22Activity.this, "查找失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });



}
