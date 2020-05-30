package cn.itcast.s.activity;
/**
 * 火车票列表显示
 * 输入时间早于当前时间，显示当前时间
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.s.Adapter.Mybase_train;
import cn.itcast.s.Class.Traininfo;
import cn.itcast.s.Class.element_s;
import cn.itcast.s.R;

public class trainlist extends AppCompatActivity {

    private static final String URL="https://api.jisuapi.com/train/station2s";//需要用到的服务器连接
    private static final String APPKEY="6c13ef428ea8111a";//个人注册获得的密钥
    private ListView listView;
    private Mybase_train adapter; //列车列表适配器
    private String time;
    element_s elementS ;
    private List<Traininfo>list =new ArrayList<>();//用来存放获得的列车信息
    //用来从线程中获取数据进行操作
    private Handler handler = new  Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0x001){
                show();//展示列车列表
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainlist);
        listView = (ListView)findViewById(R.id.listview);
        //返回按钮
        ImageView back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final Bundle bundle = getIntent().getExtras();
        String start = bundle.getString("1");
        String end = bundle.getString("2");
        String date = bundle.getString("3");
        time = date;
        int isgo = bundle.getInt("4");
        elementS = new element_s(start,end,date,isgo);
        start();//向服务器请求数据并解析
        //列表点击事件，跳转到购买列表，将对应的列车信息穿过去
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Traininfo traininfo = list.get(i);
                String start = traininfo.getStation();
                String end = traininfo.getEndstation();
                String stime = traininfo.getDeparturetime();
                String etime = traininfo.getArrivaltime();
                String train = traininfo.getTrainno();
                String type = traininfo.getType();
                String p1,p2,p3;
                //判断什么类型的车
                if (type.equals("G") ){
                    type="G";
                    p1 = traininfo.getPriceyd();
                    p2 = traininfo.getPriceed();
                    p3 = traininfo.getPricesw();
                }else if(type.equals("D")){
                    type="G";
                    p1 = traininfo.getPriceyw1();
                    p2 = traininfo.getPriceed();
                    p3 = traininfo.getPricerw1();
                }else{
                    type="K";
                    p2 = traininfo.getPriceyz();
                    p1 = traininfo.getPriceyw1();
                    p3 = traininfo.getPricerw1();
                }
                Intent intent = new Intent(trainlist.this, Buyitem.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("1",start);
                bundle1.putString("2",end);
                bundle1.putString("3",stime);
                bundle1.putString("4",etime);
                bundle1.putString("5",train);
                bundle1.putString("6",type);
                bundle1.putString("7",p1);
                bundle1.putString("8",p2);
                bundle1.putString("9",p3);
                bundle1.putString("10",time);
                intent.putExtras(bundle1);
                trainlist.this.startActivityForResult(intent,1);

            }
        });
    }
    //列车列表适配器
    private void show(){
        adapter = new Mybase_train(this,list);
        listView.setAdapter(adapter);
    }
    //包含一个线程，好像3.0以后的版本使用网络编程需要在线程中进行，不过这样跑的确实快了
    private void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    get();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //利用GET方法获取数据并解析
    private void get() throws MalformedURLException {
        list.clear();//每次必须清空
        InputStream is=null;
        //合成完整的url，通过GET方法获取数据
        String path = URL+"?appkey="+APPKEY+"&start="+elementS.getStart()+"&end="+elementS.getEnd()
                +"&ishigh="+elementS.getIsgo()+"&date="+elementS.getDate();

        java.net.URL url= new URL(path);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int responseCode = conn.getResponseCode();
            if(responseCode == 200 ){
                is = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                StringBuffer stringBuffer = new StringBuffer();
                String readline = "";

                while((readline = bufferedReader.readLine()) != null){
                    stringBuffer.append(readline);
                    is.close();
                    bufferedReader.close();
                    conn.disconnect();
                    Log.d("TAG",stringBuffer.toString());//可以在logcat里面看到数据

                    JSONObject jsonObject = new JSONObject(stringBuffer.toString());//转化成json并解析
                    JSONObject object = jsonObject.getJSONObject("result");
                    JSONArray jsonArray = object.getJSONArray("list");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject ob=jsonArray.getJSONObject(i);
                        String type = ob.optString("type");
                        if(elementS.getIsgo() == 1){ //在这里筛选，是否时高铁动车
                            if(!(type.equals("G")||type.equals("D"))) {
                                continue;
                            }
                        }
                        String departuretime = ob.optString("departuretime");
                        String arrivaltime = ob.optString("arrivaltime");
                        String station = ob.optString("station");
                        String endstation = ob.optString("endstation");
                        String trainno=ob.optString("trainno");
                        String costime = ob.optString("costtime");
                        String pricesw = ob.optString("pricesw");
                        String privetd = ob.optString("privetd");
                        String pricegr1 = ob.optString("pricegr1");
                        String pricegr2 = ob.optString("pricegr2");
                        String pricerw1 = ob.optString("pricerw1");
                        String pricerw2 = ob.optString("pricerw2");
                        String priceyw1 = ob.optString("priceyw1");
                        String priceyw2 = ob.optString("priceyw2");
                        String priceyw3 = ob.optString("priceyw3");
                        String priceyd = ob.optString("priceyd");
                        String priceed = ob.optString("priceed");
                        String pricerz = ob.optString("pricerz");
                        String priceyz = ob.optString("priceyz");

                        list.add(new Traininfo(departuretime,arrivaltime,station,endstation,trainno,
                                costime,pricesw,privetd,pricegr1,pricegr2,pricerw1,pricerw2,priceyw1,
                                priceyw2,priceyw3,priceyd,priceed,pricerz,priceyz,type));
                    }
                }

            }else{
                Log.d("TAG","failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handler.sendEmptyMessage(0x001);
    }
}
