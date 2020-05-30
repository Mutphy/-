package cn.itcast.s.Adapter;
//显示车票listview的数据适配器
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import cn.itcast.s.Class.Traininfo;
import cn.itcast.s.R;

//列车展示适配器
public class Mybase_train extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Traininfo> list;
    public Mybase_train(Context context, List<Traininfo>list){
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

    //展示各个列车信息
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(view == null){
            view = layoutInflater.inflate(R.layout.trainlistitem,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        Traininfo traininfo = (Traininfo)getItem(i);
        String stime = traininfo.getDeparturetime();
        viewHolder.stime.setText(stime);
        String price1 = traininfo.getPriceed();
        String price2 = traininfo.getPriceyz();
        if(price1.length() > 1){
            viewHolder.cost.setText("￥"+price1);
            viewHolder.p1.setText("商务座");
            viewHolder.p2.setText("一等座");
            viewHolder.p3.setText("二等座");
        }else if(price2.length() > 1){
            viewHolder.cost.setText("￥"+price2);
            viewHolder.p1.setText("软卧");
            viewHolder.p2.setText("硬卧");
            viewHolder.p3.setText("硬座");
        }
        String ctime = traininfo.getCostime();
        viewHolder.ctime.setText(ctime);
        String etime = traininfo.getArrivaltime();
        viewHolder.etime.setText(etime);
        String start = traininfo.getStation();
        viewHolder.start.setText(start);
        String end = traininfo.getEndstation();
        viewHolder.end.setText(end);
        String type = traininfo.getTrainno();
        viewHolder.type.setText(type);

        return view;
    }
    class ViewHolder{
        TextView stime,etime,ctime,start,type,end,cost,p1,p2,p3;
        public ViewHolder(View view){
            stime = view.findViewById(R.id.liststime);
            etime = view.findViewById(R.id.listetime);
            ctime = view.findViewById(R.id.longtime);
            start = view.findViewById(R.id.lists);
            type = view.findViewById(R.id.listtype);
            end = view.findViewById(R.id.liste);
            cost = view.findViewById(R.id.listcost);
            p1 = view.findViewById(R.id.p1);
            p2 = view.findViewById(R.id.p2);
            p3 = view.findViewById(R.id.p3);
        }
}

}
