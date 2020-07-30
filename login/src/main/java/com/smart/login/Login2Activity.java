package com.smart.login;

import android.os.Bundle;
import android.view.View;

import com.smart.anotation.BindPath;
import com.smart.basic.BasicActivity;
import com.smart.router.Arouter;

@BindPath("login/login2")
public class Login2Activity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    public void jump2Member(View view) {
        Arouter.getInstance().jumpActivity("member/member", null);
    }
}