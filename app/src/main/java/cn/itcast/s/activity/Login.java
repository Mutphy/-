package cn.itcast.s.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.itcast.s.DB.SQLiteHelper;
import cn.itcast.s.R;

/*
登录界面
账户密码没有设置限制，在数据库中最多是20个字符
第一次输入即为注册账户
 */
public class Login extends AppCompatActivity {


    private  SQLiteHelper sqLiteHelper;
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView back = findViewById(R.id.back);
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        sqLiteHelper = new SQLiteHelper(this);
        Button login = findViewById(R.id.login);
        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //登录操作，判断是否在数据库中，在，判断密码是否正确，否，为用户表增加一条数据，同时对应表状态设置为1
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Account = account.getText().toString();
                String Password = password.getText().toString();
                String pass = sqLiteHelper.check(Account);
                if (Account.equals("")||Password.equals("")){  //读取到的数据不能为空
                    Toast.makeText(getApplicationContext(),"无效字符！",Toast.LENGTH_SHORT).show();
                }else{
                    if (pass != null){ //此账户已存在，只需要激活
                        if (pass.equals(Password)){ //密码正确
                            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            //将账户变成激活状态
                            sqLiteHelper.updatestatus(Account,1);
                            //登录成功之后返回上一层界面
                           finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"账户或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }else{//还没有该账户
                        //创建用户
                        sqLiteHelper.insertUserData(Account,Password,1);
                        finish();
                    }
                }
            }
        });
    }
}
