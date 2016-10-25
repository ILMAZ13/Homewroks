package ru.itis.contactsv2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.List;

import ru.itis.contactsv2.ContactsActivity;
import ru.itis.contactsv2.entities.Contact;
import ru.itis.contactsv2.providers.ContactsProvider;

/**
 * Created by ilmaz on 25.10.16.
 */

public class DeletionDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private Contact contact;

    public static DeletionDialogFragment newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putSerializable("contact", contact);
        DeletionDialogFragment fragment = new DeletionDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        contact = (Contact) getArguments().getSerializable("contact");
        if(contact.isDeleted()) {
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Восстановить контакт?")
                    .setPositiveButton("Да",this)
                    .setNegativeButton("Нет", this)
                    .setMessage("Контакт будет восстановлен");
            return adb.create();
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Удалить контакт?")
                    .setPositiveButton("Да", this)
                    .setNegativeButton("Нет", this)
                    .setMessage("Контакт будет перенесён в список удалённых");
            return adb.create();
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                if(contact.isDeleted()){
                    List<Contact> contactList = ContactsProvider.getInstance().getContactsList();
                    List<Contact> deletedContactList1 = ContactsProvider.getInstance().getDeletedContactsList();
                    deletedContactList1.remove(contact);
                    contact.setDeleted(false);
                    contactList.add(contact);
                    ContactsProvider.getInstance().saveContacts(contactList);
                    ContactsProvider.getInstance().saveDeletedContacts(deletedContactList1);
                } else {
                    List<Contact> contactList = ContactsProvider.getInstance().getContactsList();
                    List<Contact> deletedContactList1 = ContactsProvider.getInstance().getDeletedContactsList();
                    contactList.remove(contact);
                    contact.setDeleted(true);
                    deletedContactList1.add(contact);
                    ContactsProvider.getInstance().saveContacts(contactList);
                    ContactsProvider.getInstance().saveDeletedContacts(deletedContactList1);
                }
                if(getActivity() instanceof ContactsActivity) {
                    ((ContactsActivity) getActivity()).getPagerAdapter().notifyDataSetChanged();
                }
                break;
        }
    }
}
