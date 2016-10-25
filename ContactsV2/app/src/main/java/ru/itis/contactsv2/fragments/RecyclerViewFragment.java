package ru.itis.contactsv2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.itis.contactsv2.R;
import ru.itis.contactsv2.adapters.ContactsRecyclerViewAdapter;
import ru.itis.contactsv2.interfaces.Updatable;

/**
 * Created by ilmaz on 23.10.16.
 */

public class RecyclerViewFragment extends Fragment implements Updatable{
    private boolean isForDeleted;
    private ContactsRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    public static RecyclerViewFragment newInstance(boolean isForDeleted) {

        Bundle args = new Bundle();
        args.putBoolean("isForDeleted", isForDeleted);
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_list, container, false);
        isForDeleted = getArguments().getBoolean("isForDeleted");

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_contacts);
        adapter = new ContactsRecyclerViewAdapter(isForDeleted, getActivity().getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
    }

}
