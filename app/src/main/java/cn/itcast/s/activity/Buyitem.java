package cn.itcast.s.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.itcast.s.DB.SQLiteHelper;
import cn.itcast.s.R;

public class Buyitem extends AppCompatActivity implements View.OnClickListener {

    private SQLiteHelper sqLiteHelper;
    private TextView p1;
    private TextView p2;
    private TextView p3;
    private String start1;
    private String end1;
    private String atime1;
    private String dtime1;
    private String traino1;
    private String time1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyitem);
        Button buy1 = findViewById(R.id.buy1);
        Button buy2 = findViewById(R.id.buy2);
        Button buy3 = findViewById(R.id.buy3);
        ImageView back = findViewById(R.id.back);
        TextView stime = findViewById(R.id.liststime);
        TextView etime = findViewById(R.id.listetime);
        TextView start = findViewById(R.id.lists);
        TextView end = findViewById(R.id.liste);
        TextView train = findViewById(R.id.listtype);
        TextView price1 = findViewById(R.id.price1);
        TextView price2 = findViewById(R.id.price2);
        TextView price3 = findViewById(R.id.price3);
        p1 = findViewById(R.id.price11);
        p2 = findViewById(R.id.price22);
        p3 = findViewById(R.id.price33);
        sqLiteHelper = new SQLiteHelper(this);
        //返回按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        start1 = bundle.getString("1");
        end1 = bundle.getString("2");
        dtime1 = bundle.getString("3");
        atime1 = bundle.getString("4");
        traino1 = bundle.getString("5");
        time1 = bundle.getString("10");
        start.setText(start1);
        end.setText(end1);
        stime.setText(dtime1);
        etime.setText(atime1);
        train.setText(traino1);
        String type = bundle.getString("6");
        p2.setText(bundle.getString("8"));
        p1.setText(bundle.getString("7"));
        p3.setText(bundle.getString("9"));
        //非高铁动车
        if (!type.equals("G")){
            price2.setText("硬座");
            price1.setText("硬卧");
            price3.setText("软卧");
        }
        //买票按钮
        buy1.setOnClickListener(this);
        buy2.setOnClickListener(this);
        buy3.setOnClickListener(this);
    }

    //购买车票
    @Override
    public void onClick(View view) {
        String price=null;
        String phone = sqLiteHelper.searchaccount();
        if (phone == null){ //手机号为空，说明没有激活状态的手机号需要先登录进行激活
            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Buyitem.this, Login.class);//跳转到登录界面
            Buyitem.this.startActivityForResult(intent,1);
        }else{
            switch (view.getId()){//获取对应的票价
                case R.id.buy2:
                    price = p2.getText().toString();
                    break;
                case R.id.buy1:
                    price = p1.getText().toString();
                    break;
                case R.id.buy3:
                    price = p3.getText().toString();
                    break;
            }
            //判断火车票是否被买过，通过判断时间和列车号来实现，同一时间同一列车号即为重复
            String account;
            account = sqLiteHelper.checckaccount(time1,traino1);
            //这里需要注意，因为所有订单都在一个表里，所以搜索时可能会有多个用户的数据，需要判断是否时当前用户
            if(account == null){
                //没有账户买过，建立火车票订单
                if(sqLiteHelper.insertOrderData(phone,time1,atime1,dtime1,start1,end1,traino1,price)){
                    Toast.makeText(getApplicationContext(),"购买成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                }
            }else{
                if(account.equals(phone)){//当前账户买过了
                    Toast.makeText(getApplicationContext(),"不得重复购买",Toast.LENGTH_SHORT).show();
                }else{
                    //当前账户没有买，建立火车票订单
                    if(sqLiteHelper.insertOrderData(phone,time1,atime1,dtime1,start1,end1,traino1,price)){
                        Toast.makeText(getApplicationContext(),"购买成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }
}
