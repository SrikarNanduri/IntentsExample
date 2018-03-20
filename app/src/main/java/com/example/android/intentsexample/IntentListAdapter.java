package com.example.android.intentsexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Srikar on 3/19/2018.
 */

public class IntentListAdapter extends
        RecyclerView.Adapter<IntentListAdapter.IntentViewHolder> {


    private ArrayList<ImageUrls> mImageButtons;
    Context context;
    private LayoutInflater mInflater;



    public IntentListAdapter(Context context, ArrayList mImageButtons) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mImageButtons = mImageButtons;
    }


    @Override
    public IntentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_intents, parent, false);
        return new IntentViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(final IntentViewHolder holder,final int position) {
        holder.mLoadingIndicator.setVisibility(View.VISIBLE);
        Picasso.with(context).load(mImageButtons.get(position).getAndroid_image_url())
                .resize(800, 500)
                .into(holder.mImageButton, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mLoadingIndicator.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareItem(mImageButtons.get(position).getAndroid_image_url());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImageButtons.size();
    }

    class IntentViewHolder extends RecyclerView.ViewHolder{

        public final ImageButton mImageButton;
        public final Button mButton;
        public  final IntentListAdapter mAdapter;
        private ProgressBar mLoadingIndicator;
        public IntentViewHolder(View itemView, IntentListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mLoadingIndicator =  itemView.findViewById(R.id.pb_loading_indicator);
            mImageButton = itemView.findViewById(R.id.imageButton);
            mButton = itemView.findViewById(R.id.share);
        }
    }



    public void shareItem(String url) {
        Picasso.with(context).load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent( Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
               context.startActivity(Intent.createChooser(i, "Share Image"));
            }
            @Override public void onBitmapFailed(Drawable errorDrawable) { }
            @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

}
