package com.example.administrator.stjdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiveSong extends Activity {


    Button start,tuichi,tiaoguo;
    String userid=null,xinxi=null,frienduserid;
    TextView gedan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_song);


        start=(Button)this.findViewById(R.id.button48);
        tuichi=(Button)this.findViewById(R.id.button47);
        tiaoguo=(Button)this.findViewById(R.id.button46);
        gedan=(TextView)this.findViewById(R.id.textView28);


        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");
        xinxi=intent.getStringExtra("xinxi");

        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);

        start.setVisibility(start.INVISIBLE);
        tuichi.setVisibility(tuichi.INVISIBLE);
        tiaoguo.setVisibility(tiaoguo.INVISIBLE);



        if (xinxi!=null) {

            tuichi.setText("推迟演唱");
            start.setVisibility(start.VISIBLE);
            tuichi.setVisibility(tuichi.VISIBLE);
            tiaoguo.setVisibility(tiaoguo.VISIBLE);
            frienduserid=xinxi.split(":")[0];
            gedan.setText("\n    收到歌单：\n\n    歌名："+xinxi.split(":")[1]+"\n    歌手："+xinxi.split(":")[2]);
        }
        else {

            tuichi.setText("返回主页");
            tuichi.setVisibility(tuichi.VISIBLE);

        }


        tuichi.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                ReceiveSong.this.finish();
            }
        });


        start.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                Map<String, String> values = new HashMap<String, String>();
                values.put("myuserid", userid.trim());
                http.Request("hadsing", values);

                PackageManager pm=getPackageManager();
                List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);


                String appdiaoyong1=null,appdiaoyong2=null;
                for (PackageInfo packageInfo:list2) {
                    String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                    String packageName = packageInfo.packageName;

                    if (appName.equals("唱吧")) {
                        appdiaoyong1 = packageName;
                    }
                    else if (appName.equals("全民K歌")){
                        appdiaoyong2 = packageName;
                    }
                }
                if (appdiaoyong1!=null){
                    startActivity(getPackageManager().getLaunchIntentForPackage(appdiaoyong1));
                }
                else if (appdiaoyong2!=null){
                    startActivity(getPackageManager().getLaunchIntentForPackage(appdiaoyong2));
                }
                else {
                    alertDialog.setTitle("系统提示").setMessage("尊敬的用户，您好！系统检测到缺少必要的组件，无法开始演唱，单击“确定”下载必要的组件安装后，即可开始演唱歌曲！").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(ReceiveSong.this, WebPageChangBaDownload.class);
//                    intent.setData(Uri.parse("http://yun.baidu.com"));
//                    intent.setAction(Intent.ACTION_VIEW);
                            startActivity(intent);
                        }
                    }).show();
                }
            }
        });

        tiaoguo.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Map<String, String> values = new HashMap<String, String>();
                values.put("myuserid", userid.trim());
                http.Request("hadsing", values);

            }
        });

    }




    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    Toast.makeText(ReceiveSong.this, "操作成功！" + http.Result, Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    //intent.putExtra("userid",username.getText().toString().trim());
                    //startActivity(intent);
                    ReceiveSong.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    Toast.makeText(ReceiveSong.this, "操作失败！"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });



}
