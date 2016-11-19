package ru.itis.megaapplicationfor10points.interfaces;

/**
 * Created by ilmaz on 19.11.16.
 */

public interface TaskListener {
    void onTaskStart();
    void onTaskFinished();
    void onTaskCanceled();
    void onTaskUpdate(int i);
}
