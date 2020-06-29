package com.nzy39.nzi39app.Net;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nzy39.nzi39app.Net.list.NetListActivity;
import com.nzy39.nzi39app.R;

public class LoginActivity extends AppCompatActivity {

    EditText mNameEditText;
    EditText mPwdEditText;
    Button mLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNameEditText = (EditText)findViewById(R.id.edt_name);
        mPwdEditText = (EditText)findViewById(R.id.edt_pwd);
        mLoginButton = (Button) findViewById(R.id.edt_commit);
        /*mContentTxt = findViewById(R.id.edt_commit);
        final EditText nameEdt = findViewById(R.id.edt_name);
        final android.widget.EditText pwdEdt = findViewById(R.id.edt_pwd);
        Button cmitBtn = findViewById(R.id.edt_commit);*/

            mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content= mNameEditText.getText().toString();
                String nameVal = mNameEditText.getText().toString().trim();
                String pwdVal = mPwdEditText.getText().toString().trim();
                if (TextUtils.isEmpty(nameVal)) {
                    Toast.makeText(com.nzy39.nzi39app.Net.LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwdVal)) {
                    Toast.makeText(com.nzy39.nzi39app.Net.LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(com.nzy39.nzi39app.Net.LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(com.nzy39.nzi39app.Net.LoginActivity.this, NetListActivity.class);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", content);
                editor.apply();

                intent.putExtra("loginName", content);
                intent.putExtra("name", nameVal);
                intent.putExtra("pwd", Integer.valueOf(pwdVal));
                startActivity(intent);
                finish();

            }

        });
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
            String username = sp.getString("username","");
            if ("".equals(username)){
                mNameEditText.setHint("请输入用户名");
            }else {
                mNameEditText.setText(username);
            }

    }
}

