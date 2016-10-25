package ru.itis.contactsv2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.itis.contactsv2.R;
import ru.itis.contactsv2.entities.Contact;

/**
 * Created by ilmaz on 25.10.16.
 */

public class ContactItemViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView number;
    private Context context;
    public ContactItemViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        name = (TextView) itemView.findViewById(R.id.name);
        number = (TextView) itemView.findViewById(R.id.number);
    }

    public void bind(Contact contact){
        name.setText(contact.getName());
        number.setText(String.valueOf(contact.getNumber()));
    }
}
