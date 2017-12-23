package com.example.shash.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.shash.bucketdrops.R;
import com.example.shash.bucketdrops.beans.Drop;
import io.realm.RealmResults;

/**
 * Created by shash on 12/15/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AdapterDrops";
    public static final int ITEM=0;
    public static final int FOOTER=1;


    private LayoutInflater mInflater;
    private RealmResults<Drop> mResults;

    public AdapterDrops(Context context, RealmResults<Drop> results){
        mInflater= LayoutInflater.from(context);
        update(results);
    }

    public void update(RealmResults<Drop> results){
        mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mResults==null || position< mResults.size()){
            return ITEM;
        }
        else {
            return FOOTER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==FOOTER){
            View view= mInflater.inflate(R.layout.footer,parent,false);
            return new Footerholder(view);
        }
        else{
            View view= mInflater.inflate(R.layout.row_drop,parent,false);
            return new Dropholder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Dropholder){
            Dropholder dropholder = (Dropholder) holder;
            Drop drop= mResults.get(position);
            dropholder.mTextWhat.setText(drop.getWhat());
            Log.d(TAG, "onBindViewHolder: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return mResults.size() + 1;
    }

    public static class Dropholder extends RecyclerView.ViewHolder{

        TextView mTextWhat;

        public Dropholder(View itemView) {
            super(itemView);
            mTextWhat= itemView.findViewById(R.id.tv_what);
        }
    }

    public static class Footerholder extends RecyclerView.ViewHolder{

        Button mBtnAdd;

        public Footerholder(View itemView) {
            super(itemView);
            mBtnAdd= itemView.findViewById(R.id.btn_footer);
        }
    }

}
