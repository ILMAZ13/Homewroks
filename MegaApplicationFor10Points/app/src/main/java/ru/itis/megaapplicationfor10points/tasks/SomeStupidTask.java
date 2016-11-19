package ru.itis.megaapplicationfor10points.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import ru.itis.megaapplicationfor10points.interfaces.TaskListener;
import ru.itis.megaapplicationfor10points.interfaces.TaskPattern;

/**
 * Created by ilmaz on 19.11.16.
 */

public class SomeStupidTask extends Fragment implements TaskPattern {
    private TaskListener listener;
    private MyTask myTask;

    @Override
    public void startTask() {
        if(myTask == null){
            myTask = new MyTask();
            myTask.execute();
        }
    }

    @Override
    public void cancelTask() {
        if(myTask != null){
            myTask.cancel(true);
            myTask = null;
        }
    }

    @Override
    public boolean isRunning() {
        return myTask != null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachListener(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachListener(activity);
    }

    private void attachListener(Context context){
        if(context instanceof TaskListener) listener = (TaskListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private class MyTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(listener != null){
                listener.onTaskStart();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myTask = null;
            if(listener != null){
                listener.onTaskFinished();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(listener != null){
                listener.onTaskUpdate(values[0]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    publishProgress(i+1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            myTask = null;
        }
    }
}
