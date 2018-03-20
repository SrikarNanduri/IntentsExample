package com.example.android.intentsexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private IntentListAdapter mAdapter;


    private final String android_image_urls[] = {
            "http://res.cloudinary.com/srikarnanduri/image/upload/v1521016291/toa-heftiba-250951-unsplash_aad8ft.jpg",
            "http://res.cloudinary.com/srikarnanduri/image/upload/v1521016288/marcus-castro-309376-unsplash_l6j0mb.jpg",
            "http://res.cloudinary.com/srikarnanduri/image/upload/v1508565298/jared-brashier-252015_mioi7d.jpg",
            "http://res.cloudinary.com/srikarnanduri/image/upload/v1508564723/radovan-paska-381890_apqpts.jpg",
            "http://res.cloudinary.com/srikarnanduri/image/upload/c_scale,w_686/v1521016302/sticker-mule-189122-unsplash_pxfhze.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        final ArrayList mImageButtons = prepareData();
        // Create an adapter and supply the data to be displayed.
        mAdapter = new IntentListAdapter(this, mImageButtons);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mAdapter.deleteFiles();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.deleteFiles();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter.deleteFiles();
    }

    private ArrayList prepareData(){

        ArrayList mList = new ArrayList<>();
        for(int i=0;i<android_image_urls.length;i++){
            ImageUrls imageUrls = new ImageUrls();
            imageUrls.setAndroid_image_url(android_image_urls[i]);
            mList.add(imageUrls);
        }
        return mList;
    }

    @Override
    public void finish() {
        super.finish();

    }
}
