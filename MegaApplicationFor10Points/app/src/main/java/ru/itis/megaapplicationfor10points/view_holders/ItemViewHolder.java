package ru.itis.megaapplicationfor10points.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.entities.Notification;

/**
 * Created by ilmaz on 19.11.16.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private TextView tvInformation;

    public ItemViewHolder(View itemView) {
        super(itemView);
        tvInformation = (TextView) itemView.findViewById(R.id.tv_text);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    public void bind(Notification notification) {
        tvTitle.setText(notification.title);
        tvInformation.setText(notification.text);
    }

}
