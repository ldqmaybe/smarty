package com.smart.member;

import android.os.Bundle;

import com.smart.anotation.BindPath;
import com.smart.basic.BasicActivity;

@BindPath("member/member")
public class MemberActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menber);
    }
}
