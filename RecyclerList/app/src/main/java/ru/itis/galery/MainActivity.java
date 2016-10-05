package ru.itis.galery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyItem[] items;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillItems();
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new MyAdapter());
    }

    private void fillItems() {
        items = new MyItem[10000];
        for (int i = 0; i < 10000; i++) {
            items[i] = new MyItem(getRandom(1000000), (getRandom(4) / 2 > 0) ? Color.RED : Color.BLUE);
        }
    }

    private int getRandom(int max) {
        return (int) Math.abs(Math.round(Math.random() * max));
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            switch (viewType) {
                case 1:
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                    return new MyViewHolder(view);
                case 0:
                    View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
                    return new MyViewHolder2(view2);
                default:
                    View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                    return new MyViewHolder(view3);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).bind(items[position]);
            } else {
                ((MyViewHolder2) holder).bind(items[position]);
            }
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        @Override
        public int getItemViewType(int position) {
            return (position % 2 == 0) ? 0 : 1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        public void bind(MyItem item) {
            textView.setText(item.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }

        public void bind(final MyItem item) {
            textView.setBackgroundColor(item.getColor());
            textView.setText(item.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, InfoActivity.class);
                    intent.putExtra("text", textView.getText().toString());
                    intent.putExtra("color", item.getColor());
                    startActivity(intent);
                }
            });
        }
    }

    class MyItem {
        private int number;
        private int color;

        public MyItem(int text, int color) {
            this.number = text;
            this.color = color;
        }

        public String getText() {
            return Integer.toString(number);
        }

        public int getColor() {
            return color;
        }

    }
}
