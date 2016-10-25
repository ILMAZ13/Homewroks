package ru.itis.contactsv2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ru.itis.contactsv2.R;
import ru.itis.contactsv2.entities.Contact;

/**
 * Created by ilmaz on 25.10.16.
 */

public class InformationFragment extends Fragment {
    private TextView name;
    private TextView number;
    private Button buttonCall;
    private Button buttonSendMessage;
    public static InformationFragment newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putSerializable("contact", contact);
        InformationFragment fragment = new InformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_information, container, false);
        final Contact contact = (Contact) getArguments().getSerializable("contact");

        name = (TextView) view.findViewById(R.id.inf_name);
        number = (TextView) view.findViewById(R.id.inf_number);

        buttonCall = (Button) view.findViewById(R.id.btn_call);
        buttonSendMessage = (Button) view.findViewById(R.id.btn_send);

        name.setText(contact.getName());
        number.setText(String.valueOf(contact.getNumber()));

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + String.valueOf(contact.getNumber())));
                    startActivity(dialIntent);
                }catch (SecurityException e){
                    Toast.makeText(getActivity(), "Permission error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("sms_body", String.valueOf(contact.getNumber()));
                startActivity(smsIntent);
            }
        });

        return view;
    }
}
