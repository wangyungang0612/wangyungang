package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wangyungang.R;

import java.util.List;


/**
 * Created by wangyungang on 2016/6/15.
 */
public class Spinner4Adapter extends BaseAdapter {
    Context context;
    private List<String> areaList;//分型线
    private List<String> doornameList;//门体型号
    private List<String> cntList;//库存
    private List<String> lackList;//报警
    private List<String> maxList;//最大

    OnItemClickLitener mOnItemClickLitener;

    public Spinner4Adapter(List areaList, List doornameList, List cntList, List lackList, List maxList, Context context) {
      //  L.e("wyg --- list : " + doorList);
        this.areaList = areaList;
        this.doornameList = doornameList;
        this.cntList = cntList;
        this.lackList = lackList;
        this.maxList = maxList;

        this.context = context;
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v;
        if(view!=null){
            v = view;
        }else {
            v = View.inflate(context, R.layout.spinner4_item, null);
        }

        TextView tv1 = (TextView) v.findViewById(R.id.area_Text);
        tv1.setText( areaList.get(position));
        TextView tv2 = (TextView) v.findViewById(R.id.doorname);
        tv2.setText( doornameList.get(position));
        TextView tv4 = (TextView) v.findViewById(R.id.cnt);
        tv4.setText( cntList.get(position));
        TextView tv5 = (TextView) v.findViewById(R.id.lack);
        tv5.setText( lackList.get(position));

//        int i = Integer.parseInt((String) cntList.get(position));
//        int j = Integer.parseInt((String) lackList.get(position));
//        int k = Integer.parseInt((String) maxList.get(position));
//        if(i<=j){
//            tv4.setTextColor(Color.YELLOW);
//
//        }
//        if(i>=j&&i>k){
//            tv4.setTextColor(Color.RED);
//        }

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        return v;
    }


}
