package ru.avinews.avinews.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.avinews.avinews.R;

/**
 * Created by ilmaz on 08.12.16.
 */

public class NewsItemViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView date;
    private TextView fullText;
    private boolean expanded;

    // TODO: 09.12.16 fill buttons
    public NewsItemViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tv_title);
        date = (TextView) itemView.findViewById(R.id.tv_date);
        fullText = (TextView) itemView.findViewById(R.id.tv_full_text);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 09.12.16 add if for checking after long click
                if(expanded){
                    fullText.setVisibility(View.GONE);
                    expanded = false;
                } else {
                    fullText.setVisibility(View.VISIBLE);
                    expanded = true;
                }
            }
        });
    }

    public void bind(){
        expanded = false;
        fullText.setVisibility(View.GONE);
    }


}
