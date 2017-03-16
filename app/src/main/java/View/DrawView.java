package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangyungang on 2016/7/12 0012.
 */
public class DrawView extends View {
    private List<Point> allPoint = new ArrayList<Point>();
    public DrawView(Context context, AttributeSet set) {
        super(context,set);
        super.setBackgroundColor(Color.WHITE);
        super.setOnTouchListener(new OnTouchListenerImpl());
    }
    private class OnTouchListenerImpl implements OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            Point p = new Point((int) event.getX(),(int) event.getY());
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                allPoint = new ArrayList<Point>();
                allPoint.add(p);
            }else if(event.getAction()==MotionEvent.ACTION_UP){
                allPoint.add(p);
                DrawView.this.postInvalidate();
            }else if(event.getAction()==MotionEvent.ACTION_MOVE){
                allPoint.add(p);
                DrawView.this.postInvalidate();
            }
            return true;
        }
    }

    protected void onDraw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.RED);
        if(allPoint.size()>1){
            Iterator<Point> iter = allPoint.iterator();
            Point first = null;
            Point last = null;
            while(iter.hasNext()){
                if(first == null){
                    first = (Point)iter.next();
                }else {
                    if(last != null){
                        first = last;
                    }
                    last = (Point)iter.next();
                    canvas.drawLine(first.x,first.y,last.x,last.y,p);

                }
            }

        }
    }
}
