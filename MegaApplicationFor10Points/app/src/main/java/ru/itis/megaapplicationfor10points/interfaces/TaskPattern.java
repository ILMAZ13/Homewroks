package ru.itis.megaapplicationfor10points.interfaces;

/**
 * Created by ilmaz on 19.11.16.
 */

public interface TaskPattern {
    void startTask();
    void cancelTask();
    boolean isRunning();
}
