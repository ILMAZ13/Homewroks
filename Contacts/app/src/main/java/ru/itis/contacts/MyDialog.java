package ru.itis.contacts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by ilmaz on 21.10.16.
 */

public class MyDialog extends DialogFragment implements DialogInterface.OnClickListener{
    private Contact contact;
    private MyAdapter adapter;
    private dialogOnClickListener listener;

    public void setInformation(Contact contact, MyAdapter adapter){
        this.contact = contact;
        this.adapter = adapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(contact.isDeleted()){
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Удалить номер?!").setPositiveButton(R.string.yes, this)
                    .setNegativeButton(R.string.no, this)
                    .setMessage(R.string.message_restore);
            return adb.create();
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                    .setTitle("Удалить номер?!").setPositiveButton(R.string.yes, this)
                    .setNegativeButton(R.string.no, this)
                    .setMessage(R.string.message);
            return adb.create();
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                listener.onYesClick(contact, adapter);
                break;
        }
    }

    public interface dialogOnClickListener{
        public void onYesClick(Contact contact, MyAdapter adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (dialogOnClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
