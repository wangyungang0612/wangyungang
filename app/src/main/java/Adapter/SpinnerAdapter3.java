package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wangyungang.R;

import java.util.List;


/**
 * Created by wangyungang on 2016/6/15.
 */
public class SpinnerAdapter3 extends BaseAdapter {
    Context context;
    List list2;
    OnItemClickLitener mOnItemClickLitener;

    public SpinnerAdapter3(List list2, Context context) {
        this.list2 = list2;
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
        return list2.size();
    }

    @Override
    public Object getItem(int position) {
        return list2.get(position);
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
            v = View.inflate(context,R.layout.spinner2_item, null);
        }

        TextView tv = (TextView) v.findViewById(R.id.spinner_Text);
        tv.setText((CharSequence) list2.get(position));
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        return v;
    }


}
