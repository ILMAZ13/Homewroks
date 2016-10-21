package ru.itis.contacts;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilmaz on 21.10.16.
 */

public class RecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected MyAdapter myAdapter;
    private MyAdapter.onSomeEventListener someEventListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rv, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        List<Contact> contacts = new LinkedList<>();

        if(getArguments() != null){
            contacts = (List<Contact>) getArguments().getSerializable("contacts");
        }

        myAdapter = new MyAdapter(someEventListener, contacts);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (MyAdapter.onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }
}
