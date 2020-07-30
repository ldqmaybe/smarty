package com.smart.main;

import android.os.Bundle;
import android.view.View;

import com.smart.anotation.BindPath;
import com.smart.basic.BasicActivity;
import com.smart.router.Arouter;

@BindPath("main/main")
public class MainActivity extends BasicActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump2Login(View view) {
        Arouter.getInstance().jumpActivity("login/login", null);
    }
}