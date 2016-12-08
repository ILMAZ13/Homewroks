package ru.avinews.avinews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.avinews.avinews.R;
import ru.avinews.avinews.adapters.NewsListAdapter;

/**
 * Created by ilmaz on 08.12.16.
 */

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsListAdapter adapter;

    // TODO: 08.12.16 add player buttons and card

    public static NewsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        adapter = new NewsListAdapter();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        
        return view;
    }
}
