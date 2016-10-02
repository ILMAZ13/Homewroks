package ru.itis.photogallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button button;
    private EditText editText;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.loading_view);
        button = (Button) findViewById(R.id.btn_search);
        editText = (EditText) findViewById(R.id.et_name);

        adapter = new MyAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                new ParseSite().execute(text);
            }
        });
    }

    private class ParseSite extends AsyncTask<String, Void, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        protected List<String> doInBackground(String... arg) {
            List<String> output = new ArrayList<>();
            try {
                HtmlHelper hh = new HtmlHelper(new URL("https://yandex.ru/images/touch/search?text="+arg[0]));
                output = hh.getImageLinks();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return output;
        }

        protected void onPostExecute(List<String> output) {
            adapter.setLinksList(output);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
