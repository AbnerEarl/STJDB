package com.example.administrator.stjdb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    Button denglu,wangjimima;
    ProgressBar denglujiazai;
    Button zhuce;
    EditText username,userpass;
    TextView tishi;
    ProgressBar progressBar;
    final String METHOD_HELLO_WORLD = "selectAllCargoInfor";
    File path;
    String path1;

    WebService http = new WebService(new RequestFunc() {

        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) { // + http.Result
                    denglujiazai.setVisibility(denglujiazai.INVISIBLE);
                    denglu.setVisibility(denglu.VISIBLE);
                    zhuce.setVisibility(zhuce.VISIBLE);
                    wangjimima.setVisibility(wangjimima.VISIBLE);
                    Toast.makeText(Main2Activity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginInfo(Main2Activity.this,username.getText().toString().trim(),userpass.getText().toString().trim(),"1");
                    Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    intent.putExtra("userid",username.getText().toString().trim());
                    startActivity(intent);
                    Main2Activity.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    denglu.setVisibility(denglu.VISIBLE);
                    zhuce.setVisibility(zhuce.VISIBLE);
                    wangjimima.setVisibility(wangjimima.VISIBLE);
                    denglujiazai.setVisibility(denglujiazai.INVISIBLE);
                    Toast.makeText(Main2Activity.this, "登录失败"+http.Result, Toast.LENGTH_SHORT).show();
                    saveLoginInfo(Main2Activity.this,username.getText().toString().trim(),userpass.getText().toString().trim(),"0");
                    //tishi.setText("服务器回复的信息 : " + http.Result);
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
      /*  if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        denglu=(Button)this.findViewById(R.id.button);
        denglujiazai=(ProgressBar)this.findViewById(R.id.progressBar);
        zhuce=(Button)this.findViewById(R.id.button3);
        wangjimima=(Button)this.findViewById(R.id.button2);
        username=(EditText)this.findViewById(R.id.editText);
        userpass=(EditText)this.findViewById(R.id.editText2);
        tishi=(TextView)this.findViewById(R.id.textView8);

   /*     if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory();
           // path1=getApplicationContext().getFilesDir().getAbsolutePath();

            //帐号保存
//        String filename1=new String("userid.txt");
//        String filename2=new String("pass.txt");
            File f1 = new File(path + "/userid.txt");
            if (!f1.exists()) {
                try {
                    f1.createNewFile();
                } catch (Exception ex) {
                    System.out.print(ex.toString());
                }
            } else {
                try {
                    FileReader fs = new FileReader(path + "/userid.txt");
                    char[] b = new char[255];
                    fs.read(b);
                    String tem = new String(b);
                    username.setText(tem.trim());

                } catch (Exception ex) {
                    System.out.print(ex.toString());
                }
            }
            File f2 = new File(path + "/password.txt");
            if (!f2.exists()) {
                try {
                    f2.createNewFile();
                } catch (Exception ex) {
                    System.out.print(ex.toString());
                }
            } else {
                try {
                    FileReader fs = new FileReader(path + "/password.txt");
                    char[] b = new char[255];
                    fs.read(b);
                    String tem = new String(b);
                    userpass.setText(tem.trim());

                } catch (Exception ex) {
                    System.out.print(ex.toString());
                }
            }
        }*/
       /* final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/

        denglu.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userin=username.getText().toString().trim();
                String userps=userpass.getText().toString().trim();
                Map<String, String> values = new HashMap<String, String>();


                if (userin==null||userin.length()<2){

                    tishi.setText("请输入用户名！");
                    username.requestFocus();

                }
                else if (userps==null||userps.length()<2){

                    tishi.setText("请输入用户密码！");
                    userpass.requestFocus();
                }else {




                    values.put("userid", userin);
                    values.put("userpass",userps);
                    denglujiazai.setVisibility(denglujiazai.VISIBLE);
                    denglu.setVisibility(denglu.INVISIBLE);
                    zhuce.setVisibility(zhuce.INVISIBLE);
                    wangjimima.setVisibility(wangjimima.INVISIBLE);
                    http.Request("dengluxinxi", values);
//                Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
//                startActivity(intent);
//                    thread.start();


                }

                //无网络环境测试
                /*Toast.makeText(Main2Activity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                intent.putExtra("userid","18140650917");
                startActivity(intent);
                Main2Activity.this.finish();*/



            }
        });
        zhuce.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main25Activity.class);
                startActivity(intent);
            }
        });










        SharedPreferences sharedPre=getSharedPreferences("config", MODE_PRIVATE);
        String personusername=sharedPre.getString("username", "");
        String personpassword=sharedPre.getString("password", "");
        String personflag=sharedPre.getString("flag", "");

        if (personusername.trim().length()>2&&personpassword.trim().length()>2){

            username.setText(personusername);
            userpass.setText(personpassword);

            if (personflag.trim().equals("3")){
                String userin=username.getText().toString().trim();
                String userps=userpass.getText().toString().trim();
                Map<String, String> values = new HashMap<String, String>();
                values.put("userid", userin);
                values.put("userpass",userps);
                denglujiazai.setVisibility(denglujiazai.VISIBLE);
                denglu.setVisibility(denglu.INVISIBLE);
                zhuce.setVisibility(zhuce.INVISIBLE);
                wangjimima.setVisibility(wangjimima.INVISIBLE);
                //shiyong.setVisibility(shiyong.INVISIBLE);
                http.Request("dengluxinxi", values);

            }


        }




    }









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






    /**
     * 从assets目录下读取个人文件信息文件内容
     * @param fileName
     * @return
     */
    public String getFromAssets(String fileName){
        String result="";
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);

            if((result = bufReader.readLine()) != null){
                result= result.trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }






    /**
     * 使用SharedPreferences保存用户登录信息
     * @param context
     * @param username
     * @param password
     *  @param flag
     */
    public static void saveLoginInfo(Context context, String username, String password, String flag){
        //获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("flag", flag);
        //提交
        editor.commit();
    }





}
