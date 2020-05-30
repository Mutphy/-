package cn.itcast.s.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.itcast.s.Class.TrainOrder;
import cn.itcast.s.R;

//订单适配器
public class Mybase_order extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<TrainOrder> list;
    //构造函数
    public Mybase_order(Context context, List<TrainOrder>list){
        layoutInflater= LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //展示订单
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = layoutInflater.inflate(R.layout.orderlistitem,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        TrainOrder trainOrder = (TrainOrder)getItem(i);
        String start = trainOrder.getStart();
        viewHolder.start.setText(start);
        String end = trainOrder.getEnd();
        viewHolder.end.setText(end);
        String traino = trainOrder.getTraino();
        viewHolder.traino.setText(traino);
        String time = trainOrder.getTime();
        viewHolder.time.setText(time);
        String dtime = trainOrder.getDtime();
        viewHolder.dtime.setText(dtime+"出发");
        String price = trainOrder.getPrice();
        viewHolder.price.setText("￥"+ price);

        return view;
    }
    //好像用这个方法可以稍微优化一下
    class  ViewHolder{
        TextView start,end,traino,time,dtime,price;
        public ViewHolder(View view){
            start = view.findViewById(R.id.start);
            end = view.findViewById(R.id.end);
            traino = view.findViewById(R.id.traino);
            time = view.findViewById(R.id.time);
            dtime = view.findViewById(R.id.dtime);
            price = view.findViewById(R.id.price);

        }
    }
}
