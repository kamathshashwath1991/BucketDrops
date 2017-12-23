package com.example.shash.bucketdrops.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.shash.bucketdrops.R;

/**
 * Created by shash on 12/16/2017.
 */

public class Divider extends RecyclerView.ItemDecoration {

    private static final String TAG = "Divider";
    
    private Drawable mDivider;
    private int mOrientation;

    public Divider(Context context, int orientation){
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        if (orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("This Item Decoration can be used only with a RecylerView that uses LinearLayout Manager with vertical  orientation");
        }
        mOrientation= orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL){
            drawHorrizontalDivider(c,parent,state);
        }
    }

    private void drawHorrizontalDivider(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left,right,top,bottom;
        left= parent.getPaddingLeft();
        right= parent.getWidth()- parent.getPaddingRight();
        int count = parent.getChildCount();
        for (int i = 0; i< count; i++){
            View current = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) current.getLayoutParams();
            top = current.getTop()- params.topMargin;
            bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
