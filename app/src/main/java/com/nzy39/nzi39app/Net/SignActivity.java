package com.nzy39.nzi39app.Net;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nzy39.nzi39app.Net.list.NetListActivity;
import com.nzy39.nzi39app.R;

public class SignActivity extends AppCompatActivity {
    EditText mEditText;
    Button mDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mEditText = (EditText) findViewById(R.id.sign_text);
        mDoneButton = (Button) findViewById(R.id.sign_button);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zy = mEditText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("mySign", zy);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


     /*   mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignActivity.this, NetListActivity.class);
                startActivityForResult(intent,REQUEST_CODE_EDT);
            }

        });*/

    }
}
