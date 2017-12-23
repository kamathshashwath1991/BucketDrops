package com.example.shash.bucketdrops;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shash.bucketdrops.adapters.AdapterDrops;
import com.example.shash.bucketdrops.adapters.Divider;
import com.example.shash.bucketdrops.beans.Drop;
import com.example.shash.bucketdrops.widgets.BucketRecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by shash on 12/11/2017.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Toolbar mToolbar;
    Button mBtnAdd;
    Realm mRealm;
    BucketRecyclerView mRecyclerView;
    RealmResults<Drop> mResults;
    AdapterDrops mAdapter;
    View mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mEmptyView=findViewById(R.id.empty_drops);
        mBtnAdd= findViewById(R.id.btn_add);
        setSupportActionBar(mToolbar);

        mRealm = Realm.getDefaultInstance();
        mResults = mRealm.where(Drop.class).findAllAsync();

        mRecyclerView=findViewById(R.id.rv_drops);
        mAdapter= new AdapterDrops(this,mResults);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL));

        mRecyclerView.hideIfEmpty(mToolbar);
        mRecyclerView.showIfEmpty(mEmptyView);

        initBackgroundImage();
        mBtnAdd.setOnClickListener(mBtnAddListener);
    }

    private void initBackgroundImage(){
        ImageView background = findViewById(R.id.iv_background);
        Glide.with(this)
                .load(R.drawable.background)
                .into(background);
    }

    private View.OnClickListener mBtnAddListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showDialogAdd();
        }
    };

    private void showDialogAdd() {
        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(),"Add");
    }

    private RealmChangeListener mChangeListener = new RealmChangeListener() {
        @Override
        public void onChange(Object o) {
            Log.d(TAG, "onChange: ");
            mAdapter.update(mResults);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mResults.addChangeListener(mChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListener(mChangeListener);
    }
}
