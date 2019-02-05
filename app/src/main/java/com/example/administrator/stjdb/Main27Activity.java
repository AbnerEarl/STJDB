package com.example.administrator.stjdb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Main27Activity extends AppCompatActivity {
    Button kuaichuan;
    Button lanya,xinqingriji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);


        xinqingriji=(Button)this.findViewById(R.id.button26);
        final AlertDialog.Builder alertDialog  =new AlertDialog.Builder(this);
        lanya=(Button)this.findViewById(R.id.button20);
        lanya.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getPackageManager();
                List<PackageInfo> list2 = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);


                String appdiaoyong = null;
                for (PackageInfo packageInfo : list2) {
                    String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString().trim();
                    String packageName = packageInfo.packageName;

                    if (appName.equals("蓝牙共享")||appName.equals("蓝牙")) {
                        appdiaoyong = packageName;
                    }
                }
                if (appdiaoyong != null) {
                    startActivity(getPackageManager().getLaunchIntentForPackage(appdiaoyong));
                } else {

                   /* setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    })*/

                    alertDialog.setTitle("系统提示").setMessage("尊敬的用户，您好！您尚未开通皇级服务，无法体验该功能，单击“确定”前往开通！").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(Main27Activity.this, Main28Activity.class);
//                    intent.setData(Uri.parse("http://yun.baidu.com"));
//                    intent.setAction(Intent.ACTION_VIEW);
                            startActivity(intent);
                        }
                    }).show();



                }
            }
        });


        kuaichuan=(Button)this.findViewById(R.id.button21);
                kuaichuan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getPackageManager();
                List<PackageInfo> list2 = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);


                String appdiaoyong = null;
                for (PackageInfo packageInfo : list2) {
                    String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                    String packageName = packageInfo.packageName;

                    if (appName.equals("快传")) {
                        appdiaoyong = packageName;
                    }
                }
                if (appdiaoyong != null) {
                    startActivity(getPackageManager().getLaunchIntentForPackage(appdiaoyong));
                } else {
                    alertDialog.setTitle("系统提示").setMessage("尊敬的用户，您好！您尚未开通皇级服务，无法体验该功能，单击“确定”前往开通！").setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(Main27Activity.this, Main28Activity.class);
//                    intent.setData(Uri.parse("http://yun.baidu.com"));
//                    intent.setAction(Intent.ACTION_VIEW);
                            startActivity(intent);
                        }
                    }).show();
                }
            }
        });



        xinqingriji.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main27Activity.this,Main210Activity.class);
                startActivity(intent);
            }
        });


    }
}
