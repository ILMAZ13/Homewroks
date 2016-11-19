package ru.itis.megaapplicationfor10points.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.itis.megaapplicationfor10points.R;
import ru.itis.megaapplicationfor10points.activities.EditActivity;
import ru.itis.megaapplicationfor10points.activities.ListActivity;
import ru.itis.megaapplicationfor10points.entities.Notification;
import ru.itis.megaapplicationfor10points.providers.NotificationProvider;

/**
 * Created by ilmaz on 19.11.16.
 */

public class InformationFragment extends Fragment {
    private Notification notification;
    private int position;
    private TextView tvTitle;
    private TextView tvText;
    private Button button;

    public static InformationFragment newInstance(Notification notification, int position) {

        Bundle args = new Bundle();
        args.putSerializable("notification", notification);
        args.putInt("position", position);
        InformationFragment fragment = new InformationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        notification = (Notification) getArguments().getSerializable("notification");
        position = getArguments().getInt("position");

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvText = (TextView) view.findViewById(R.id.tv_text);
        button = (Button) view.findViewById(R.id.btn_edit);

        fillInformation();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra("notification", notification);
                startActivityForResult(intent, position);
            }
        });

        return view;
    }

    private void fillInformation() {
        if(notification != null) {
            tvTitle.setText(notification.title);
            tvText.setText(notification.text);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == EditActivity.EDITED_KEY){
            Notification notification = (Notification) data.getSerializableExtra("notification");
            if(notification != null){
                List<Notification>  notifications = NotificationProvider.getInstance().getNotificationList(getActivity());
                notifications.get(requestCode).text = notification.text;
                notifications.get(requestCode).title = notification.title;
                NotificationProvider.getInstance().writeNotificationList(getActivity(), notifications);
                this.notification = notification;
                fillInformation();
                if(getActivity() instanceof ListActivity){
                    ((ListActivity) getActivity()).notifyDataSetChanged();
                }
                Toast.makeText(getActivity(), "Notification changed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
