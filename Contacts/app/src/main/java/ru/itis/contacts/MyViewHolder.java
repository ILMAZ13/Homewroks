package ru.itis.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ilmaz on 21.10.16.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_number);
    }

    public void bind(Contact number){
        textView.setText(Long.toString(number.getNumber()));
    }
}
