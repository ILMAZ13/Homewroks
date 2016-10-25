package ru.itis.contactsv2.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.itis.contactsv2.R;
import ru.itis.contactsv2.entities.Contact;
import ru.itis.contactsv2.fragments.DeletionDialogFragment;
import ru.itis.contactsv2.fragments.InformationFragment;
import ru.itis.contactsv2.providers.ContactsProvider;

/**
 * Created by ilmaz on 25.10.16.
 */

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Contact> contacts;
    private boolean isForDeleted;
    private FragmentManager manager;
    private FragmentActivity activity;

    public ContactsRecyclerViewAdapter(boolean isForDeleted, FragmentActivity activity) {
        this.activity = activity;
        this.manager = activity.getSupportFragmentManager();
        this.isForDeleted = isForDeleted;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ContactItemViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Contact contact = contacts.get(position);
        ((ContactItemViewHolder) holder).bind(contact);
        ((ContactItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationFragment fragment = InformationFragment.newInstance(contact);
                manager.popBackStack();
                manager.beginTransaction()
                        .replace(R.id.fl_info, fragment, InformationFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
            }
        });
        ((ContactItemViewHolder) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DeletionDialogFragment fragment = DeletionDialogFragment.newInstance(contact);
                fragment.show(manager, DeletionDialogFragment.class.getName());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        contacts = getContacts(isForDeleted);
        return contacts.size();
    }

    private List<Contact> getContacts(boolean isForDeleted){
        if(isForDeleted){
            return ContactsProvider.getInstance().getDeletedContactsList(activity);
        } else {
            return ContactsProvider.getInstance().getContactsList(activity);
        }
    }


}
