package com.example.administrator.stjdb;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

public class Main25Activity extends AppCompatActivity {


 /*   EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            //LogUtils.i("event:"+event+"    result:"+result+"    data:"+data.toString());
            switch (event) {
                case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        toast("验证成功");
                    } else {
                        toast("验证失败");
                    }
                    break;
                case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        toast("获取验证码成功");
                        //默认的智能验证是开启的,我已经在后台关闭
                    } else {
                        toast("获取验证码失败");
                    }
                    break;
            }
        }
    };
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main25Activity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }*/






    WebService http=new WebService(new RequestFunc() {
        @Override
        public void Func() {
            if (http.Result != null) {
                if (http.Result.equals("true")) {
                    zhucejiazai.setVisibility(zhucejiazai.INVISIBLE);
                    zhuce.setVisibility(zhuce.VISIBLE);
                    huoqu.setVisibility(huoqu.VISIBLE);
                    Toast.makeText(Main25Activity.this, "注册成功" + http.Result, Toast.LENGTH_SHORT).show();
                    //Intent intent=new Intent(Main2Activity.this,Main22Activity.class);
                    //intent.putExtra("userid",username.getText().toString().trim());
                    //startActivity(intent);
                    Main25Activity.this.finish();
                    //denglu.setText("服务器回复的信息 : " + http.Result);
                } else {
                    zhucejiazai.setVisibility(zhucejiazai.INVISIBLE);
                    zhuce.setVisibility(zhuce.VISIBLE);
                    huoqu.setVisibility(huoqu.VISIBLE);
                    Toast.makeText(Main25Activity.this, "注册失败"+http.Result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    EditText nicheng,shouji,mima1,mima2,yanzhengma;
    TextView shuoming;
    ProgressBar zhucejiazai;
    Button zhuce,huoqu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);



        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());





        //SMSSDK.initSDK(this, "bed3c2b8490b", "aa7933d433ce4a6b7b1d895f93312fef");
        //初始化
       // cn.smssdk.SMSSDK.initSDK(this, "你的AppKey", "你的AppSecret");
        //发送短信
       // cn.smssdk.SMSSDK.getVerificationCode("86", "18612121304");

        nicheng=(EditText)this.findViewById(R.id.editText3);
        shouji=(EditText)this.findViewById(R.id.editText4);
        mima1=(EditText)this.findViewById(R.id.editText5);
        mima2=(EditText)this.findViewById(R.id.editText6);
        yanzhengma=(EditText)this.findViewById(R.id.editText9);
        zhuce=(Button)this.findViewById(R.id.button11);
        huoqu=(Button)this.findViewById(R.id.button35);
        zhucejiazai=(ProgressBar)this.findViewById(R.id.progressBar2);
        shuoming=(TextView)this.findViewById(R.id.textView15);



        zhuce.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String SMS = yanzhengma.getText().toString().trim();
                String phone = shouji.getText().toString();
               // SMSSDK.submitVerificationCode("86", phone, SMS);


               /* EventHandler eh=new EventHandler(){

                    @Override
                    public void afterEvent(int event, int result, Object data) {

                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //回调完成
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                                //提交验证码成功
                            }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                                //获取验证码成功
                            }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                                //返回支持发送验证码的国家列表
                            }
                        }else{
                            ((Throwable)data).printStackTrace();
                        }
                    }
                };
                 SMSSDK.registerEventHandler(eh); //注册短信回调
*/
                if(panduan()){
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("userid", shouji.getText().toString().trim());
                    values.put("userpass",mima1.getText().toString().trim());
                    values.put("username", nicheng.getText().toString().trim());
                    zhucejiazai.setVisibility(zhucejiazai.VISIBLE);
                    zhuce.setVisibility(zhuce.INVISIBLE);
                    huoqu.setVisibility(huoqu.INVISIBLE);
                    http.Request("zhucexinxi",values);

                }

            }
        });

        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);
        huoqu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouji.getText().toString().trim()==null||shouji.getText().toString().trim().length()!=11)
                {
                    alertDialog.setTitle("              系统提示").setMessage("请输入正确的手机号码！！").setPositiveButton("确定", null).show();

                }
                else {
                    alertDialog.setTitle("              系统提示").setMessage("你已经被管理员特别关注，并给你赋予了特权，不需要验证码即可注册！").setPositiveButton("确定", null).show();
                }
                //getSMS();
//                String phone = shouji.getText().toString().trim();
//                cn.smssdk.SMSSDK.getVerificationCode("86", phone);
            }
        });






    }

    public Boolean panduan(){
        if (nicheng.getText().toString().trim()==null||nicheng.getText().toString().trim().length()<2||nicheng.getText().toString().trim()==""){
            nicheng.requestFocus();
            shuoming.setText("昵称不能为空！");

            return false;
        }
        else if (shouji.getText().toString().trim()==null||shouji.getText().toString().trim()==""||shouji.getText().toString().trim().length()<2){
            shouji.requestFocus();
            shuoming.setText("手机号码不能为空！");

            return false;
        }
        else if (mima1.getText().toString().trim()==null||mima1.getText().toString().trim()==""||mima1.getText().toString().trim().length()<2){
            mima1.requestFocus();
            shuoming.setText("设置密码不能为空！");

            return false;
        }
        else if(mima2.getText().toString().trim()==null||mima2.getText().toString().trim()==""||mima2.getText().toString().trim().length()<2){
            mima2.requestFocus();
            shuoming.setText("确认密码不能为空！");
            return false;
        }
        else if (!mima2.getText().toString().trim().equals(mima1.getText().toString().trim())){
            mima2.setText("");
            mima2.requestFocus();
            shuoming.setText("两次密码不一致，请重新输入！");
            return false;
        }
        else if (shouji.getText().toString().trim().length()!=11 ){
            shuoming.setText("手机号码输入不合法！");
            return false;

        }
        return true;
    }





  /*  private void getSMS() {
        String phone = shouji.getText().toString();
        SMSSDK.getVerificationCode("86", phone, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        });
    }*/
  /*  public void onClick() {
        String SMS=yanzhengma.getText().toString().trim();
        String phone = shouji.getText().toString();
        SMSSDK.submitVerificationCode("86", phone, SMS);
    }*/






}
