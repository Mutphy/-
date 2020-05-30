/**
 * 作者：杜鹏飞
 * 仿智行火车票
 * 只是模拟相关的过程，并没有实际用处
 * 火车票的数据由极速数据网址提供
 * https://www.jisuapi.com/
 * 其中APPKEY需要自己从网站注册账户获取
 * 用户可以免费测试100次数据，之后需要付款
 * 该项目仅用于安卓结课作业
 * 火车票数据实时更新
 */
package cn.itcast.s.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.itcast.s.Class.Traininfo;
import cn.itcast.s.Class.element_s;
import cn.itcast.s.DB.SQLiteHelper;
import cn.itcast.s.R;
/*
首页
进行查询火车票操作
 */
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private List<Traininfo>list = new ArrayList<>();
    private SQLiteHelper sqLiteHelper;
    element_s elementS = new element_s();
    private EditText e_start;
    private EditText e_end;
    private EditText e_date;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e_date=findViewById(R.id.time);
        e_end=findViewById(R.id.edit_end);
        e_start=findViewById(R.id.edit_start);
        ImageView shift=findViewById(R.id.swith);
        sqLiteHelper = new SQLiteHelper(this);
        //设置转换按钮，让始发站和终点站交换
        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String swap;
                swap=e_start.getText().toString();
                e_start.setText(e_end.getText().toString());
                e_end.setText(swap);
            }
        });
        gettime();//获取时间
        /*
        本来想用radiobutton的但是这个按钮不能第二次取消点击，只能换成这个了，没想到还挺好用的。
        记录是否需要高铁
         */
        CheckBox cb=findViewById(R.id.rb);
        cb.setOnCheckedChangeListener(this);

        //查询火车并跳转到列车列表中去
        Button button=findViewById(R.id.sclik);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elementS.setEnd(e_end.getText().toString());
                elementS.setStart(e_start.getText().toString());
                elementS.setDate(e_date.getText().toString());
                Intent intent = new Intent(MainActivity.this,trainlist.class);
                Bundle bundle = new Bundle();
                bundle.putString("1",elementS.getStart());
                bundle.putString("2",elementS.getEnd());
                bundle.putString("3",elementS.getDate());
                bundle.putInt("4",elementS.getIsgo());
                intent.putExtras(bundle); //将需要查询的数据传入
                MainActivity.this.startActivityForResult(intent,1);

            }
        });

        //我的 界面
        ImageView mine = findViewById(R.id.mine);
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = sqLiteHelper.searchaccount();
                if (status == null){ //没有登录，跳转到登录界面
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    MainActivity.this.startActivityForResult(intent,1);
                }else{  //已登录，跳转到登录成功界面
                    finish();//每一次下面按钮进行跳转时，都需要把当前页面销毁，不然只会越来越多
                    Intent intent = new Intent(MainActivity.this, Login_success.class);
                    MainActivity.this.startActivityForResult(intent,1);
                }

            }
        });
        //订单界面
        ImageView order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(MainActivity.this,Orderlist.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });
    }
    //选择是否高铁动车的点击事件
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            elementS.setIsgo(1);
        }
        else{
            elementS.setIsgo(0);
        }
    }
    //获取当前系统时间
    private  void gettime(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String now = date.format(new Date());
        e_date.setText(now);
    }
}
