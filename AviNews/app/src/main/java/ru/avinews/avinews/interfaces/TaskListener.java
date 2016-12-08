package ru.avinews.avinews.interfaces;

import ru.avinews.avinews.entities.Response;

/**
 * Created by ilmaz on 08.12.16.
 */

public interface TaskListener {
    void onTaskStart();
    void onTaskFinish(Response response);
}
