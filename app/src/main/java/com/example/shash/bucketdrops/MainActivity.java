package com.example.shash.bucketdrops;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by shash on 12/11/2017.
 */

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button mBtnAdd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mBtnAdd= findViewById(R.id.btn_add);
        setSupportActionBar(mToolbar);
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

}
