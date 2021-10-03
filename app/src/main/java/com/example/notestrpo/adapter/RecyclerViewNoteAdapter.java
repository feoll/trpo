package com.example.notestrpo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notestrpo.R;
import com.example.notestrpo.activity.EditActivity;
import com.example.notestrpo.activity.MainActivity;
import com.example.notestrpo.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewNoteAdapter extends RecyclerView.Adapter<RecyclerViewNoteAdapter.ViewHolder> {

    private List<NoteModel> list;
    private Context context;

    public RecyclerViewNoteAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_main_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.bind(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("EditNote", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }

        public void bind(int i) {
            String titleStr = list.get(i).getTitle();
            String subtitleStr = list.get(i).getSubtitle();

            if (titleStr.isEmpty()) {
                title.setText("Без заголовка");
                title.setTextColor(context.getResources().getColor(R.color.colorGray));
            } else {
                title.setText(titleStr);
                title.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }

            if (subtitleStr.isEmpty()) {
                subtitle.setText("Без подзаголовка");
                subtitle.setTextColor(context.getResources().getColor(R.color.colorGray));
            } else {
                subtitle.setText(subtitleStr);
                subtitle.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }

        }
    }

    public void updateAdapter(List<NoteModel> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

}
