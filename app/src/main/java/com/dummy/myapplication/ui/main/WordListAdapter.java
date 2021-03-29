package com.dummy.myapplication.ui.main;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.dummy.myapplication.data.local.entity.Word;


public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {

    interface ItemClickHandler{
        void onItemClicked(View view, int position);
        boolean onItemLongClicked(View view, int position);
    }
    private final ItemClickHandler itemClickHandler;
    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback, ItemClickHandler itemClickHandler) {
        super(diffCallback);
        this.itemClickHandler = itemClickHandler;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord());
        if(itemClickHandler != null) {
            holder.itemView.setOnLongClickListener(v -> itemClickHandler.onItemLongClicked(v, position));
            holder.itemView.setOnClickListener(v -> itemClickHandler.onItemClicked(v, position));
        }
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}
