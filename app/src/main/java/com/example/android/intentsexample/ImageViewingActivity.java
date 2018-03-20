package com.example.android.intentsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * Created by srikarn on 20-03-2018.
 */

public class ImageViewingActivity extends AppCompatActivity {
    public ImageView mImageView;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimage);
        mLoadingIndicator =  findViewById(R.id.pb_loading_indicator2);

        Bundle b = getIntent().getExtras();
       String imgUrl = b.getString("ÙRL");
        System.out.println( b.getString("ÙRL"));
        mImageView =  findViewById(R.id.image);
       mLoadingIndicator.setVisibility(View.VISIBLE);
        Picasso.with(ImageViewingActivity.this).load(imgUrl)
                .into(mImageView, new Callback() {
                    @Override
                    public void onSuccess() {mLoadingIndicator.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
