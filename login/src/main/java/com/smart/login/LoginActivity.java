package com.smart.login;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.smart.anotation.BindPath;
import com.smart.basic.BasicActivity;
import com.smart.router.Arouter;

@BindPath("login/login")
public class LoginActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.i("LoginActivity onCreate : ");
    }

    public void jump2Member(View view) {
        Arouter.getInstance().jumpActivity("login/login2", null);
    }
}
