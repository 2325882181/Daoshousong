package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.dao.CustomerDao;
import com.example.daoshousong.R;
import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    EditText etname;
    EditText etpwd;
    Button btnlogin;
    Button btnregister;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnregister = (Button)findViewById(R.id.btnregister);
        etname = (EditText)findViewById(R.id.etname);
        etpwd = (EditText)findViewById(R.id.etpassword);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                String pwd = etpwd.getText().toString();
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                intent1.putExtra("id",4);
                startActivity(intent1);
                /*CustomerDao pd = new CustomerDao(LoginActivity.this);
                if(name == ""&&pwd == ""){
                    Toast.makeText(LoginActivity.this,"您未输入用户名与密码",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "输出"+pd.find(name).getCname());
                }else if(pd.getCount() == 0){
                    Toast.makeText(LoginActivity.this,"您未注册",Toast.LENGTH_SHORT).show();
                }else if(pd.find(name).getCname().equals(name)&&pd.find(name).getCpwd().equals(pwd)){
                    startActivity(intent1);
                }else{
                    Toast.makeText(LoginActivity.this,"你输入的用户名或密码有误",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
            }
        });
    }
}
