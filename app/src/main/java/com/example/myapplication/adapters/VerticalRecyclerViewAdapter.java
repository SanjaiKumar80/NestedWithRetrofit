package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Android;
import com.example.myapplication.Model.Category;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewHolder> {

    private Context mContext;
    private ArrayList<Category> mArrayList ;

    public VerticalRecyclerViewAdapter(Context mContext, ArrayList<Category> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new VerticalRecyclerViewHolder(view);
    }

    public void setList(ArrayList<Category> mArrayList) {
        this.mArrayList.addAll(mArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VerticalRecyclerViewHolder holder, int position) {

        final Category current = mArrayList.get(position);

        final String strTitle = current.getName();

        List<Android> singleSectionItems = current.getAndroid();

        holder.tvTitle.setText(strTitle);

        HorizontalRecyclerViewAdapter itemListDataAdapter = new HorizontalRecyclerViewAdapter(mContext, singleSectionItems);

        holder.rvHorizontal.setHasFixedSize(true);
        holder.rvHorizontal.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        holder.rvHorizontal.setAdapter(itemListDataAdapter);

        holder.rvHorizontal.setNestedScrollingEnabled(false);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, current.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        RecyclerView rvHorizontal;
        Button btnMore;

        public VerticalRecyclerViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvHorizontal = itemView.findViewById(R.id.rvHorizontal);
            btnMore = itemView.findViewById(R.id.btnMore);

        }
    }
}
