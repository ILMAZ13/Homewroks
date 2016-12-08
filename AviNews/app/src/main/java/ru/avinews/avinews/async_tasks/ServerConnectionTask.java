package ru.avinews.avinews.async_tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.avinews.avinews.entities.Response;
import ru.avinews.avinews.interfaces.TaskListener;

/**
 * Created by ilmaz on 08.12.16.
 */

public class ServerConnectionTask extends Fragment {
    private TaskListener listener;
    private SomeTask task;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachContext(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachContext(activity);
    }

    private void attachContext(Context context){
        if(context instanceof TaskListener){
            listener = (TaskListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void startTask(){
        if(task == null){
            task = new SomeTask();
            task.execute();
        }
    }

    public boolean isRunning(){
        return task != null;
    }

    private class SomeTask extends AsyncTask<Void, Void, Response>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(listener != null){
                listener.onTaskStart();
            }
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            task = null;
            if(listener != null){
                listener.onTaskFinish(response);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            task = null;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Response response = new Response();
            response.success = true;
            return response;
        }
    }
}
