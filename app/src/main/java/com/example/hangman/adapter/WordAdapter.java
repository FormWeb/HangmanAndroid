package com.example.hangman.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hangman.R;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<String> words;

    private final OnWordItemDeleteClickListener onWordItemDeleteClickListener;
    private final OnWordItemClickListener onWordItemClickListener;

    public WordAdapter(List<String> words, OnWordItemDeleteClickListener onWordItemDeleteClickListener, OnWordItemClickListener onWordItemClickListener) {
        this.words = words;
        this.onWordItemDeleteClickListener = onWordItemDeleteClickListener;
        this.onWordItemClickListener = onWordItemClickListener;
    }

    public interface OnWordItemClickListener {
        void onWordItemClick(int position);
    }

    public interface OnWordItemDeleteClickListener {
        void onWordItemClick(String word);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < words.size()) {
            holder.tvWord.setText(words.get(position));
        }
        else {
            holder.tvWord.setText("+ Add new text");
            holder.btnDelete.setVisibility(View.INVISIBLE);
        }

        holder.btnDelete.setOnClickListener(view -> {
            onWordItemDeleteClickListener.onWordItemClick(holder.tvWord.getText().toString());
        });

    }

    @Override
    public int getItemCount() {
        return words.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_item_word);
            btnDelete = itemView.findViewById(R.id.btn_delete_item);
            itemView.setOnClickListener(this::onClick);
        }

        public void onClick(View view) {
            onWordItemClickListener.onWordItemClick(getLayoutPosition());
        }
    }
}
