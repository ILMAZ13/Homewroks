package ru.itis.contacts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

/**
 * Created by ilmaz on 21.10.16.
 */

public class MyDialog extends DialogFragment implements DialogInterface.OnClickListener{
    private List<Contact> contacts;
    private List<Contact> delContacts;
    private int position;
    private MyAdapter adapter;

    public void setInformation(List<Contact> contacts, List<Contact> delContacts, int position, MyAdapter adapter){
        this.contacts = contacts;
        this.delContacts = delContacts;
        this.position = position;
        this.adapter = adapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Удалить номер?!").setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setMessage(R.string.message);
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                Contact contact = contacts.get(position);
                contacts.remove(position);
                delContacts.add(contact);
                adapter.notifyDataSetChanged();
        }
    }
}
