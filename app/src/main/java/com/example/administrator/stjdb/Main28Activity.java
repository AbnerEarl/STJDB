package com.example.administrator.stjdb;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Main28Activity extends AppCompatActivity {
    String appdiaoyong=null;
    Button zhifubao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);

        zhifubao=(Button)this.findViewById(R.id.button22);
        zhifubao.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Main22ActivityXiangMu.this, Main3ActivityZiYuan.class);
//                startActivity(intent);
//                finish();

                getAllAppNames();
                if (appdiaoyong != null) {
                    startActivity(getPackageManager().getLaunchIntentForPackage(appdiaoyong));
                } else {
                    Intent intent = new Intent();
                    //下载
                    //intent.setData(Uri.parse("http://mobile.baidu.com/#/item?docid=8653239&source=aladdin@wise_app_13@title&ala=wise_app@strong@%E6%94%AF%E4%BB%98%E5%AE%9D"));
                    //登录
                    intent.setData(Uri.parse("https://auth.alipay.com/login/index.htm"));

                    intent.setAction(Intent.ACTION_VIEW);
                    startActivity(intent);
                }


            }
        });
    }

    public void getAllAppNames(){
        PackageManager pm=getPackageManager();
        List<PackageInfo> list2=pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);


        for (PackageInfo packageInfo:list2){
            String appName=packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            String packageName=packageInfo.packageName;

            if (packageName.equals("com.eg.android.AlipayGphone")){
                appdiaoyong=packageName;
            }

        }
    }
}
