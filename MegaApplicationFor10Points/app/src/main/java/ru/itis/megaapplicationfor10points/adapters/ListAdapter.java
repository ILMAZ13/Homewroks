package ru.itis.megaapplicationfor10points.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.entities.Notification;
import ru.itis.megaapplicationfor10points.fragments.InformationFragment;
import ru.itis.megaapplicationfor10points.providers.NotificationProvider;
import ru.itis.megaapplicationfor10points.view_holders.ItemViewHolder;

/**
 * Created by ilmaz on 19.11.16.
 */

public class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Notification> notifications;
    private Context context;
    private FragmentManager manager;


    public ListAdapter(FragmentActivity activity) {
        context = activity;
        this.manager = activity.getSupportFragmentManager();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Notification notification = notifications.get(position);
        holder.bind(notification);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformationFragment fragment = InformationFragment.newInstance(notification, position);
                manager.popBackStack();
                manager.beginTransaction()
                        .replace(R.id.information, fragment, InformationFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        notifications = NotificationProvider.getInstance().getNotificationList(context);
        return notifications.size();
    }
}
