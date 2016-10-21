package ru.itis.contacts;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  MyAdapter.onSomeEventListener{

    private List<Contact> contacts;
    private List<Contact> deletedContacts;
    private boolean isBigMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isBigMode = (findViewById(R.id.fl_inform) != null);

        fillContacts();

        Fragment fragment = new RecyclerViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("contacts", (Serializable) contacts);
        bundle.putSerializable("delContacts", (Serializable) deletedContacts);
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fl_list, fragment, RecyclerViewFragment.class.getName())
                .commit();

    }

    private void fillContacts(){
        contacts = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            contacts.add(new Contact(89510660000L + i * 20 + i * 8));
        }
        deletedContacts = new LinkedList<>();
    }

    @Override
    public void onClick(Contact contact) {
        Fragment fragment = new InformFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);
        fragment.setArguments(bundle);
        if(isBigMode) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fl_inform, fragment, InformFragment.class.getName())
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fl_list, fragment, InformFragment.class.getName())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onLongClick(int position, MyAdapter adapter) {
        MyDialog dialog = new MyDialog();
        dialog.setInformation(contacts, deletedContacts, position, adapter);
        dialog.show(getFragmentManager(), MyDialog.class.getName());
    }
}
