package ru.itis.contacts;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ilmaz on 21.10.16.
 */

public class InformFragment extends Fragment {
    private TextView textView;
    private Button send;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_information, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.tv_number);
        send = (Button) view.findViewById(R.id.btn_send);
        Contact contact;
        if(getArguments() != null){
            contact = (Contact) getArguments().getSerializable("contact");
            textView.setText(Long.toString(contact.getNumber()));
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDO: set listener
            }
        });
    }
}
