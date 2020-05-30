package cn.itcast.s.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.s.Adapter.Mybase_order;
import cn.itcast.s.DB.SQLiteHelper;
import cn.itcast.s.Class.TrainOrder;
import cn.itcast.s.R;
//订单列表
public class Orderlist extends AppCompatActivity {


    private Mybase_order adapter;//订单适配器
    private List<TrainOrder>list = new ArrayList<>();
    private ListView listView;
    private SQLiteHelper sqLiteHelper;//数据库操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        listView =findViewById(R.id.listview);
        sqLiteHelper = new SQLiteHelper(this);
        show();//展示订单
        //首页
        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Orderlist.this,MainActivity.class);
                Orderlist.this.startActivityForResult(intent,1);
            }
        });
        //我的 界面
        ImageView mine = findViewById(R.id.mine);
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(Orderlist.this,Login_success.class);
                Orderlist.this.startActivityForResult(intent,1);
            }
        });
    }
    //listview 展示
    private void show(){
        String phone = sqLiteHelper.searchaccount();
        if (phone == null){
            Intent intent = new Intent(Orderlist.this,Login.class);
            Orderlist.this.startActivityForResult(intent,1);
        }else{
            list = sqLiteHelper.query(phone);
            adapter = new Mybase_order(this,list);
            listView.setAdapter(adapter);
        }

    }

}
