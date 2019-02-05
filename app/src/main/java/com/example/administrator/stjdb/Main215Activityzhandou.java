package com.example.administrator.stjdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main215Activityzhandou extends AppCompatActivity {
    TextView xiaoxi;
    Button jieshou,hulue;
    String userid=null,xinxi=null,tag=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main215_activityzhandou);


        Intent intent=getIntent();
        userid= intent.getStringExtra("userid").trim();
        xinxi=intent.getStringExtra("xinxi").trim();
        tag=intent.getStringExtra("tag").trim();

        xiaoxi=(TextView)this.findViewById(R.id.textView24);
        jieshou=(Button)this.findViewById(R.id.button37);
        hulue=(Button)this.findViewById(R.id.button38);

        if (tag.equals("1")) {
            hulue.setText("忽略");
            xiaoxi.setText("来自：\n\n         " + xinxi.trim()+"                     \n\n的挑战！！");
        }
        else if (tag.equals("2")){
            jieshou.setVisibility(jieshou.INVISIBLE);
            hulue.setText("返回");
            xiaoxi.setText("苍茫大地辽无垠！\n\n" + xinxi.trim()+"\n\n挥剑直指断天涯！");
        }
        else{
            jieshou.setVisibility(jieshou.INVISIBLE);
            hulue.setText("返回");
        }


        hulue.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Main215Activityzhandou.this.finish();

            }
        });

        jieshou.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main215Activityzhandou.this,Main24Activity.class);
                intent.putExtra("userid",userid);
                intent.putExtra("friendid","");
                intent.putExtra("tag","0");
                startActivity(intent);
                Main215Activityzhandou.this.finish();

            }


        });





    }
}
