package ru.avinews.avinews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.avinews.avinews.R;
import ru.avinews.avinews.view_holders.NewsItemViewHolder;

/**
 * Created by ilmaz on 08.12.16.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsItemViewHolder> {
    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 40;
    }
}
