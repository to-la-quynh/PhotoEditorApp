package com.example.photoeditor.filters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoeditor.R;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder>  {
    private FilterListener filterListener;
    ArrayList<FilterModel> filterList;
    Context context;

    public FilterAdapter(ArrayList<FilterModel> filterList, Context context) {
        this.filterList = filterList;
        this.context = context;
    }

    public void setFilterListener(FilterListener filterListener) {
        this.filterListener = filterListener;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FilterModel item = filterList.get(position);
        holder.filterPreviewImg.setImageResource(item.getFilterPreviewImg());
        holder.filterName.setText(item.getFilterName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterListener.onFilterSelected(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(filterList != null){
            return filterList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements FilterListener {
        ImageView filterPreviewImg;
        TextView filterName;

        public ImageView getFilterPreviewImg() {
            return filterPreviewImg;
        }

        public void setFilterPreviewImg(ImageView filterPreviewImg) {
            this.filterPreviewImg = filterPreviewImg;
        }

        public TextView getFilterName() {
            return filterName;
        }

        public void setFilterName(TextView filterName) {
            this.filterName = filterName;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filterPreviewImg = (ImageView)itemView.findViewById(R.id.imageFilterPreview);
            filterName = (TextView) itemView.findViewById(R.id.filterName);
            itemView.setOnClickListener((View.OnClickListener)this);
        }

        @Override
        public void onFilterSelected(View view, int position) {
            filterListener.onFilterSelected(view, this.getAdapterPosition());
        }
    }
}
