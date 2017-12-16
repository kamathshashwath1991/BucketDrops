package com.example.shash.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shash.bucketdrops.R;
import com.example.shash.bucketdrops.beans.Drop;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by shash on 12/15/2017.
 */

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.Dropholder> {
    private static final String TAG = "AdapterDrops";

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
    public Dropholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.row_drop,parent,false);
        Dropholder holder= new Dropholder(view);
        Log.d(TAG, "onCreateViewHolder: ");

        return holder;
    }

    @Override
    public void onBindViewHolder(Dropholder holder, int position) {
        Drop drop= mResults.get(position);
        holder.mTextWhat.setText(drop.getWhat());
        Log.d(TAG, "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public static class Dropholder extends RecyclerView.ViewHolder{

        TextView mTextWhat;

        public Dropholder(View itemView) {
            super(itemView);
            mTextWhat= itemView.findViewById(R.id.tv_what);
        }
    }

}
