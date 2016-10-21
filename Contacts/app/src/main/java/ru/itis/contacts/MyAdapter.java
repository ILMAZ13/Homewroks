package ru.itis.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ilmaz on 21.10.16.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private onSomeEventListener someEventListener;
    private List<Contact> numbers;

    public MyAdapter(onSomeEventListener someEventListener, List<Contact> numbers) {
        this.someEventListener = someEventListener;
        this.numbers = numbers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).bind(numbers.get(position));
        ((MyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.onClick(numbers.get(position));
            }
        });
        ((MyViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                someEventListener.onLongClick(position, MyAdapter.this);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return numbers.size();
    }


    public interface onSomeEventListener {
        public void onClick(Contact contact);
        public void onLongClick(int position, MyAdapter adapter);
    }

}
