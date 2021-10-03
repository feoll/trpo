package com.example.notestrpo.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notestrpo.R;
import com.example.notestrpo.model.ColorModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecyclerViewColorAdapter extends RecyclerView.Adapter<RecyclerViewColorAdapter.ViewHolder> {

    private Context context;
    private List<ColorModel> list;

    public RecyclerViewColorAdapter(Context context, List<ColorModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_color_picker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        public MaterialCardView colorButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            colorButton = itemView.findViewById(R.id.color_picker);
        }

        public void bind(int i){
            colorButton.setBackgroundTintList(ContextCompat.getColorStateList(context,list.get(i).getColor()));
        }
    }
}
