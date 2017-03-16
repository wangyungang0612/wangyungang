package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.administrator.wangyungang.R;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context myContext;
    private int imgRes[] = new int[]{R.drawable.people2,R.drawable.people4,
                                       R.drawable.people5,R.drawable.people6};
    public GalleryAdapter(Context c){
        this.myContext = c;
    }
    @Override
    public int getCount() {
        return this.imgRes.length;//返回图片个数
    }

    @Override
    public Object getItem(int i) {//取得指定位置的图片
        return this.imgRes[i];
    }

    @Override
    public long getItemId(int i) {//取得指定位置的图片
        return this.imgRes[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imag = new ImageView(this.myContext);
        imag.setBackgroundColor(0xFFFFFFFF);
        imag.setImageResource(this.imgRes[i]);
        imag.setScaleType(ImageView.ScaleType.CENTER);
        imag.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imag;
    }
}
