package com.example.myapplication.adapters;

import android.content.Context;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Android;
import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by malik on 2/12/17.
 */

public class HorizontalRecyclerViewAdapter extends
        RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalViewHolder> {

    private Context mContext;
    private List<Android> mArrayList;

    public HorizontalRecyclerViewAdapter(Context mContext, List<Android> mArrayList) {
        this.mContext = mContext;
        this.mArrayList =  mArrayList;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, int position) {
        final Android current = mArrayList.get(position);
        holder.txtTitle.setText(current.getTitle());
        Glide.with(mContext)
                .load(current.getImageUrl())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, current.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
ImageView image;
        public HorizontalViewHolder(View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.txtTitle);
            image =itemView.findViewById(R.id.ivThumb);
        }
    }
}
