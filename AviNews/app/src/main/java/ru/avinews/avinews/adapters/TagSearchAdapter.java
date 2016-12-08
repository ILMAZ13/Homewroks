package ru.avinews.avinews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.avinews.avinews.R;
import ru.avinews.avinews.view_holders.TagSearchViewHolder;

/**
 * Created by ilmaz on 09.12.16.
 */

public class TagSearchAdapter extends RecyclerView.Adapter<TagSearchViewHolder> {


    @Override
    public TagSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_search, parent, false);
        return new TagSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagSearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
