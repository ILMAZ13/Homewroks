package ru.avinews.avinews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.avinews.avinews.R;
import ru.avinews.avinews.view_holders.TagListViewHolder;

/**
 * Created by ilmaz on 09.12.16.
 */

public class TagListAdapter extends RecyclerView.Adapter<TagListViewHolder> {
    @Override
    public TagListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_list, parent, false);
        return new TagListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
