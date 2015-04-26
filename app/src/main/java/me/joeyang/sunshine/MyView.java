package me.joeyang.sunshine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by joe on 15-04-25.
 */
public class MyView  extends View {
    public MyView(Context context){
        super(context);
    }
    public MyView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }
    public MyView(Context context, AttributeSet attributeSet,
                  int DefaultStyle){
        super(context, attributeSet, DefaultStyle);
    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec){
        int hSpecMode = MeasureSpec.getMode(hMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(wMeasureSpec);

        int hSpecSize = MeasureSpec.getSize(hMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(wMeasureSpec);

        int myHeight = hSpecSize;
        int myWidth = wSpecSize;

        if (hSpecMode== MeasureSpec.EXACTLY){
            myHeight = hSpecSize;
        }
        else if (hSpecMode == MeasureSpec.AT_MOST){
        }

        if (wSpecMode== MeasureSpec.EXACTLY){
            myWidth = wSpecSize;
        }
        else if (wSpecMode == MeasureSpec.AT_MOST){
        }

        setMeasuredDimension(myWidth, myHeight);
    }


}
