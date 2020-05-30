package cn.itcast.s.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.s.Class.TrainOrder;

//数据库的各种操作
public class SQLiteHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    //建数据库
    public  SQLiteHelper(Context context){
        super(context,Utils.DATABASE_NAME,null,Utils.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }
    //建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS orderlist (id integer primary key autoincrement," +
                " account varchar(20), time varchar(20), atime varchar(20), dtime varchar(20), " +
                "start varchar(20), ends varchar(20), traino varchar(20), price varchar(20))");
        db.execSQL("create table IF NOT EXISTS user(account varchar(20) primary key, password varchar(20), f integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
    //添加订单数据
    public boolean insertOrderData(String account, String time,String atime,String dtime,String start,
                              String end,String traino,String price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.ACCOUNT,account);
        contentValues.put(Utils.TIME,time);
        contentValues.put(Utils.ARIVIETIME,atime);
        contentValues.put(Utils.DEPATURETIME,dtime);
        contentValues.put(Utils.START,start);
        contentValues.put(Utils.END,end);
        contentValues.put(Utils.TRAINO,traino);
        contentValues.put(Utils.PRICE,price);
        return
                sqLiteDatabase.insert(Utils.DATABASE_TABLE,null,contentValues)>0;
    }
    //添加用户数据
    public boolean insertUserData(String account,String password,int f){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.ACCOUNT,account);
        contentValues.put(Utils.PASSWORD,password);
        contentValues.put(Utils.F,f);
        return
                sqLiteDatabase.insert(Utils.DATABASE_TABLE2,null,contentValues)>0;
    }
    //查询账户对应的密码
    public String check(String account){
        String password=null;
        Cursor cursor = sqLiteDatabase.query(Utils.DATABASE_TABLE2,null,"account = ?"
                ,new String[]{account},null,null,null);
        if (cursor.getCount()!=0){
            while(cursor.moveToNext()){
                password = cursor.getString(cursor.getColumnIndex(Utils.PASSWORD));
            }
        }
        cursor.close();
        return password;
    }
    //查询激活状态下的账户——可以判断是否是激活状态
    public String searchaccount(){
        String account=null;
        Cursor cursor = sqLiteDatabase.query(Utils.DATABASE_TABLE2,null,"f = 1"
                ,null,null,null,null);
        if (cursor.getCount()!=0){
            while(cursor.moveToNext()){
                account = cursor.getString(cursor.getColumnIndex(Utils.ACCOUNT));
            }
        }
        cursor.close();
        return account;
    }
    //查询对应时间和车次下的账户——用来判断是否重复购票
    public  String checckaccount(String time,String traino){
        String account = null;
        Cursor cursor = sqLiteDatabase.query(Utils.DATABASE_TABLE,null,"time = ? and traino = ?"
                ,new String[]{time,traino},null,null,null);
        if (cursor.getCount() != 0){
            while(cursor.moveToNext()){
                account = cursor.getString(cursor.getColumnIndex(Utils.ACCOUNT));
            }
        }
        cursor.close();
        return  account;
    }
    //更新状态，激活状态 1 ——休眠状态 0
    public boolean updatestatus(String account,int f){
        ContentValues values = new ContentValues();
        values.put("f",f);
        return
                sqLiteDatabase.update(Utils.DATABASE_TABLE2,values,"account = ?",new String[]{account}) > 0;
    }
    //查询订单
    public List<TrainOrder> query(String account){
        List<TrainOrder> list = new ArrayList<TrainOrder>();
        Cursor cursor = sqLiteDatabase.query(Utils.DATABASE_TABLE,null,"account = ?",new String[]{account},
                null,null,null);
        if (cursor != null){
            while(cursor.moveToNext()){
                TrainOrder trainOrder = new TrainOrder();
                String id = String .valueOf(cursor.getInt
                        (cursor.getColumnIndex(Utils.ID)));
                String atime = cursor.getString(cursor.getColumnIndex
                        (Utils.ARIVIETIME));
                String dtime = cursor.getString(cursor.getColumnIndex
                        (Utils.DEPATURETIME));
                String start = cursor.getString(cursor.getColumnIndex
                        (Utils.START));
                String end = cursor.getString(cursor.getColumnIndex
                        (Utils.END));
                String traino =cursor.getString(cursor.getColumnIndex
                        (Utils.TRAINO));
                String time = cursor.getString(cursor.getColumnIndex
                        (Utils.TIME));
                String price = cursor.getString(cursor.getColumnIndex
                        (Utils.PRICE));
                trainOrder.setId(id);
                trainOrder.setAccount(account);
                trainOrder.setAtime(atime);
                trainOrder.setDtime(dtime);
                trainOrder.setStart(start);
                trainOrder.setEnd(end);
                trainOrder.setTime(time);
                trainOrder.setTraino(traino);
                trainOrder.setPrice(price);
                list.add(trainOrder);
            }
            cursor.close();
        }
        return  list;
    }

}
