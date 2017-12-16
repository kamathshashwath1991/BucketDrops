package com.example.shash.bucketdrops.extras;

import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by shash on 12/16/2017.
 */

public class Util {

    public static void showViews(List<View> views){
        for (View view : views){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideViews(List<View> views){
        for (View view : views){
            view.setVisibility(View.GONE);
        }
    }

}
