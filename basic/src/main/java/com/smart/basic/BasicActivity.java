package com.smart.basic;

import android.os.Bundle;

import com.smart.router.Arouter;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author LinDingQiang
 * @time 2020/7/29 18:40
 * @email dingqiang.l@verifone.cn
 */
public class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Arouter.getInstance().init(this);
    }
}
