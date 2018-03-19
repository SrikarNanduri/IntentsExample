package com.example.android.intentsexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(final IntentViewHolder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return mImageButtons.size();
    }

    class IntentViewHolder extends RecyclerView.ViewHolder{

        public final ImageButton mImageButton;
        public  final IntentListAdapter mAdapter;
        private ProgressBar mLoadingIndicator;
        public IntentViewHolder(View itemView, IntentListAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            mLoadingIndicator =  itemView.findViewById(R.id.pb_loading_indicator);
            mImageButton = itemView.findViewById(R.id.imageButton);

        }
    }
}
