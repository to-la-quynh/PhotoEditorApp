package com.example.photoeditor.transform;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoeditor.R;

import java.util.ArrayList;

public class TransformAdapter extends RecyclerView.Adapter<TransformAdapter.ViewHolder>{
    TransformListener transformListener;
    ArrayList<TransformModel> transformList;
    Context context;

    public TransformAdapter(ArrayList<TransformModel> transformList, Context context) {
        this.transformList = transformList;
        this.context = context;
    }

    public void setTransformListener(TransformListener transformListener) {
        this.transformListener = transformListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transform_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TransformModel item = transformList.get(position);
        holder.transformIcon.setImageResource(item.getTransformIconRs());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transformListener.onTransformSelected(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(transformList != null){
            return transformList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView transformIcon;

        public ImageView getTransformIcon() {
            return transformIcon;
        }

        public void setTransformIcon(ImageView transformIcon) {
            this.transformIcon = transformIcon;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transformIcon = (ImageView) itemView.findViewById(R.id.transformItem);
            itemView.setOnClickListener((View.OnClickListener)this);
        }

        @Override
        public void onClick(View view) {
            transformListener.onTransformSelected(view, this.getAdapterPosition());
        }
    }
}