package com.example.administrator.stjdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main22Activity extends AppCompatActivity {

    Button second,shitou,boke,tianjia,liaotian,duichuan,xiaoxi,gaobai,zhandou,shouhou;
    String userid;
    int tag=0,tag1=0,tag2=0;
    TextView zhanghao;
    String xinxi=null,zhanji=null,zhanjixinxi=null;
    String[]color={"red","blue","green"};
    int[]colour={0,125,255};
    private WebView webview;


    //AlertDialog isExit = new AlertDialog.Builder(this).create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main22);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent=getIntent();
        userid= intent.getStringExtra("userid");
        System.out.print("帐号："+userid);


        //实例化WebView对象
        //webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        //webview.getSettings().setJavaScriptEnabled(true);

        xiaoxi=(Button)this.findViewById(R.id.button28);
        gaobai=(Button)this.findViewById(R.id.button31);
        boke=(Button)this.findViewById(R.id.button14);
        shitou=(Button)this.findViewById(R.id.button4);
        second=(Button)this.findViewById(R.id.button5);
        tianjia=(Button)this.findViewById(R.id.button6);
        liaotian=(Button)this.findViewById(R.id.button12);
        duichuan=(Button)this.findViewById(R.id.button13);
        zhandou=(Button)this.findViewById(R.id.button36);
        zhanghao=(TextView)this.findViewById(R.id.textView14);
        shouhou=(Button)this.findViewById(R.id.button39);
        zhanghao.setText("用户："+userid+"，欢迎使用！");

        xiaoxi.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  isExit.setTitle("系统提示");


                isExit.setMessage("确定要退出吗");


                isExit.setButton("确定", listener);

                isExit.setButton2("取消", listener);

                isExit.show();

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);*/

                if (xinxi!=null){
                Intent intent = new Intent(Main22Activity.this, Main213Activity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("xinxi", xinxi);
                startActivity(intent);
                xinxi = null;
                xiaoxi.setText("我的信笺");
                }
                else {
                    Intent intent = new Intent(Main22Activity.this, Main213Activity.class);
                    intent.putExtra("userid", "");
                    intent.putExtra("xinxi", "");
                    startActivity(intent);
                }
            }


        });


        zhandou.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (zhanji!=null) {
                    Intent intent = new Intent(Main22Activity.this, Main215Activityzhandou.class);
                    intent.putExtra("userid", userid);
                    intent.putExtra("xinxi", zhanji);
                    intent.putExtra("tag","1");
                    startActivity(intent);
                    zhanji = null;
                    zhandou.setText("我的战斗");
                }
                else if (zhanji==null&&zhanjixinxi!=null){
                    Intent intent = new Intent(Main22Activity.this, Main215Activityzhandou.class);
                    intent.putExtra("userid", "");
                    intent.putExtra("xinxi", zhanjixinxi);
                    intent.putExtra("tag","2");
                    startActivity(intent);
                    zhanjixinxi = null;
                    zhandou.setText("我的战斗");
                }
                else
                {
                    Intent intent = new Intent(Main22Activity.this, Main215Activityzhandou.class);
                    intent.putExtra("userid", "");
                    intent.putExtra("xinxi", "");
                    intent.putExtra("tag","3");
                    startActivity(intent);
                }

            }


        });






        gaobai.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main22Activity.this,Main211Activity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }


        });

        second.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main22Activity.this,Main23Activity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });
        tianjia.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main22Activity.this,Main29Activity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);

            }
        });
        liaotian.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main22Activity.this,Main26Activity.class);
                //intent.putExtra("xinxi",editText.getText().toString().trim()+","+editText2.getText().toString().trim());
                startActivity(intent);

            }
        });
        duichuan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main22Activity.this, Main27Activity.class);
                //intent.putExtra("xinxi",editText.getText().toString().trim()+","+editText2.getText().toString().trim());
                startActivity(intent);

            }
        });

        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);
        shitou.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setTitle("              系统提示").setMessage("点击姿势不正确，无法进入！本系统设有人体姿势感应系统，请先站立正确的S型姿势，然后点击！").setPositiveButton("确定", null).show();

            }
        });

        shouhou.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Main22Activity.this,JingJingDeShouHou.class);
                intent1.putExtra("userid",userid);
                startActivity(intent1);
            }
        });

        boke.setOnClickListener(new Button.OnClickListener(){




            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent();
                //下载
                //intent.setData(Uri.parse("http://mobile.baidu.com/#/item?docid=8653239&source=aladdin@wise_app_13@title&ala=wise_app@strong@%E6%94%AF%E4%BB%98%E5%AE%9D"));
                //登录
                intent.setData(Uri.parse("http://blog.sina.com.cn/ycj20"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);*/
                String str = "http://blog.sina.com.cn/ycj20";
                Intent intent1=new Intent(Main22Activity.this,WebPageBlog.class);
                intent1.putExtra("url",str);
                startActivity(intent1);

            }






        });



        //广告片
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run () {

                //信息
                Map<String, String> values = new HashMap<String, String>();
                values.put("userid", userid.trim());
                //values.put("frienduserid",friendid.trim());
                http1.Request("chaxunxiaoxi", values);

                if (xinxi!=null){

                    //@android:color/holo_blue_light
                    xiaoxi.setText("我的信笺    +1");
                    //xiaoxi.setBackgroundResource(R.color.colorPrimary);
                }else {
                    xiaoxi.setText("我的信笺");
                }



                //战斗
                Map<String, String> values2 = new HashMap<String, String>();
                values2.put("userid", userid.trim());
                //values.put("frienduserid",friendid.trim());
                http2.Request("chaxunzhandou", values2);

                Map<String, String> values3 = new HashMap<String, String>();
                values3.put("myid", userid.trim());
                //values.put("frienduserid",friendid.trim());
                http3.Request("lastresult", values3);




                if (zhanji!=null||zhanjixinxi!=null){

                    //@android:color/holo_blue_light
                    zhandou.setText("我的战斗    +1");
                    //xiaoxi.setBackgroundResource(R.color.colorPrimary);
                }else {
                    zhandou.setText("我的战斗");
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
                xinxi=http1.Result.toString().trim().split(":")[1];
            } else {
                xinxi=null;
                //Toast.makeText(Main22Activity.this, "查找失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });



    WebService http2=new WebService(new RequestFunc() {
        @Override
        public void Func() {

            if (http2.Result!= null&&http2.Result.toString().trim().split(":")[0].trim().equals("true")) {
                zhanji=http2.Result.toString().trim().split(":")[1];
            } else {
                zhanji=null;
                //Toast.makeText(Main22Activity.this, "查找失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });



    WebService http3=new WebService(new RequestFunc() {
        @Override
        public void Func() {

            if (http3.Result!= null&&http3.Result.toString().trim().split(":")[0].trim().equals("true")) {
                zhanjixinxi=http3.Result.toString().trim().split(":")[1];
            } else {
                zhanjixinxi=null;
                //Toast.makeText(Main22Activity.this, "查找失败" + http1.Result, Toast.LENGTH_SHORT).show();
            }
        }
    });








    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            // 创建退出对话框
            android.app.AlertDialog isExit = new android.app.AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗？");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }
    /**监听对话框里面的button点击事件*/
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case android.app.AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case android.app.AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };









/*

   //返回————退出
    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event)

    {

        if (keyCode == KeyEvent.KEYCODE_BACK )

        {
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle("系统提示");
            isExit.setMessage("确定要退出吗");
            isExit.setButton("确定", listener);

            isExit.setButton2("取消", listener);
            isExit.show();
        }

        return false;

    }

    */
/*监听对话框里面的button点击事件*//*


    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()

    {

        public void onClick(DialogInterface dialog, int which)

        {

            switch (which)

            {

                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序

                    finish();

                    break;

                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框

                    break;

                default:

                    break;

            }

        }

    };

*/



}
