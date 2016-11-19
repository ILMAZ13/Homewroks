package ru.itis.megaapplicationfor10points.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.itis.megaapplicationfor10points.entities.Notification;

/**
 * Created by ilmaz on 19.11.16.
 */
public class NotificationProvider {
    private static NotificationProvider ourInstance = new NotificationProvider();
    private static final String PREF_KEY = "notifications";
    private static final String NOTIFICATIONS = "notificationsList";


    public static NotificationProvider getInstance() {
        return ourInstance;
    }

    private NotificationProvider() {
    }

    public void writeNotificationList(@NonNull Context context, @NonNull List<Notification> notifications){
        context = context.getApplicationContext();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Notification>>(){}.getType();
        String json = gson.toJson(notifications, type);
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NOTIFICATIONS, json);
        editor.commit();
    }

    public List<Notification> getNotificationList(@NonNull Context context){
        context = context.getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        String json = preferences.getString(NOTIFICATIONS, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Notification>>(){}.getType();
        List<Notification> notifications = gson.fromJson(json, type);
        if(notifications == null){
            Log.e("MyTAG", "Fill notifications");
            notifications = fillNotifications();
            writeNotificationList(context, notifications);
        }
        return notifications;
    }

    private List<Notification> fillNotifications(){
        List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Notification notification = new Notification();
            notification.title = "Title Number " + (i + 1);
            notification.text = i + "  Студент, об отдыхе забудь,\n" +
                    "Коль сессии пришла пора,\n" +
                    "Учебник нужный раздобудь,\n" +
                    "И изучай его с утра.\n" +
                    "\n" +
                    "Чтоб мысли в голове теснились,\n" +
                    "А по ночам конспекты снились.\n" +
                    "\n" +
                    "С утра до вечера зубри,\n" +
                    "Шпаргалок гору заготовь,\n" +
                    "Не ешь, не пей, почти умри,\n" +
                    "Экзамен сдашь — воскреснешь вновь!\n" +
                    "__________________________________\n"+
                    "Встану рано утром,\n" +
                    "Выпью чашку ртути,\n" +
                    "И пойду скончаюсь\n" +
                    "В этом институте…\n" +
                    "\n" +
                    "Друг, не надо ртути,\n" +
                    "Лучше выпей чаю\n" +
                    "Все равно все сдохнем,\n" +
                    "Сессию встречая…";
            notifications.add(notification);
        }
        return notifications;
    }
}
