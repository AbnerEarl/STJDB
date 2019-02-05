package com.example.administrator.stjdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main214Activity extends AppCompatActivity {

    Button huiyuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main214);


        huiyuan=(Button)this.findViewById(R.id.button34);
        huiyuan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main214Activity.this,Main28Activity.class);
                //intent.putExtra("xinxi",editText.getText().toString().trim()+","+editText2.getText().toString().trim());
                startActivity(intent);

            }
        });
    }
}
