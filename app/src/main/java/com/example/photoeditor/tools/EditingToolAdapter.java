package com.example.photoeditor.tools;

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

public class EditingToolAdapter extends RecyclerView.Adapter<EditingToolAdapter.ViewHolder> {
    ToolListener toolListener;
    ArrayList<ToolModel> toolList;
    Context context;

    public EditingToolAdapter(ArrayList<ToolModel> toolList, Context context) {
        this.toolList = toolList;
        this.context = context;
    }

    public void setToolListener(ToolListener toolListener) {
        this.toolListener = toolListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.editing_tool_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ToolModel item = toolList.get(position);
        holder.toolIcon.setImageResource(item.getToolIconRs());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolListener.onToolSelected(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(toolList != null){
            return toolList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
        ImageView toolIcon;

        public ImageView getToolIcon() {
            return toolIcon;
        }

        public void setToolIcon(ImageView toolIcon) {
            this.toolIcon = toolIcon;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            toolIcon = (ImageView)itemView.findViewById(R.id.editingToolItem);
            itemView.setOnClickListener((View.OnClickListener)this);
        }

        @Override
        public void onClick(View view) {
            toolListener.onToolSelected(view, this.getAdapterPosition());
        }
    }
}
