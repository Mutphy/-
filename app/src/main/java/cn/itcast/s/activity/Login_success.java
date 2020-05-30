package cn.itcast.s.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.itcast.s.DB.SQLiteHelper;
import cn.itcast.s.R;
//登录成功界面
public class Login_success extends AppCompatActivity {


    private SQLiteHelper sqLiteHelper;
    private TextView phone;
    private Button out;
    private ImageView order;
    private ImageView demo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        sqLiteHelper = new SQLiteHelper(this);
        phone = findViewById(R.id.phone);
        out = findViewById(R.id.out);
        order = findViewById(R.id.order);
        demo = findViewById(R.id.home);
        final String getphone = sqLiteHelper.searchaccount();
        //记录当前激活状态下的账户
        phone.setText(getphone);
        //首页
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Login_success.this,MainActivity.class);
                Login_success.this.startActivityForResult(intent,1);
            }
        });
        //登出按钮
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sqLiteHelper.updatestatus(getphone,0)){
                    Toast.makeText(getApplicationContext(),"已退出",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(Login_success.this,MainActivity.class);
                    Login_success.this.startActivityForResult(intent,1);
                }else{
                    Toast.makeText(getApplicationContext(),"状态信息错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //订单界面
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Login_success.this,Orderlist.class);
                Login_success.this.startActivityForResult(intent,1);
            }
        });
    }
}
