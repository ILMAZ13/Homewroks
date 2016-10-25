package ru.itis.contactsv2.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import ru.itis.contactsv2.entities.Contact;

/**
 * Created by ilmaz on 24.10.16.
 */
public class ContactsProvider {
    private static ContactsProvider ourInstance;
    private static final String CONTACTS_PREFERENCES = "contacts";
    private static final String CONTACTS_NAME = "contacts";
    private static final String DELETED_CONTACTS_NAME = "deletedContacts";

    public static ContactsProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new ContactsProvider();
        }
        return ourInstance;
    }

    private ContactsProvider() {
    }


    public List<Contact> getContactsList(@NonNull Context context){
        SharedPreferences preferences = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(CONTACTS_NAME)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(CONTACTS_NAME, "");
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            List<Contact> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        } else {
            List<Contact> contacts;
            contacts = generateContactsList();
            saveContacts(contacts, context);
            return contacts;
        }
    }

    public List<Contact> getDeletedContactsList(@NonNull Context context){
        SharedPreferences preferences = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        if(preferences.contains(DELETED_CONTACTS_NAME)) {
            Gson gson = new Gson();
            String jsonText = preferences.getString(DELETED_CONTACTS_NAME, "");
            Type listType = new TypeToken<List<Contact>>(){}.getType();
            List<Contact> contacts = gson.fromJson(jsonText, listType);
            return contacts;
        } else {
            List<Contact> contacts;
            contacts = new LinkedList<>();
            saveDeletedContacts(contacts, context);
            return contacts;
        }
    }

    public void saveContacts(List<Contact> contactList,@NonNull Context context){
        SharedPreferences preferences = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Contact>>(){}.getType();
        String jsonText = gson.toJson(contactList, listType);
        editor.putString(CONTACTS_NAME, jsonText);
        editor.commit();
    }

    public void saveDeletedContacts(List<Contact> contactList,@NonNull Context context){
        SharedPreferences preferences = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Contact>>(){}.getType();
        String jsonText = gson.toJson(contactList, listType);
        editor.putString(DELETED_CONTACTS_NAME, jsonText);
        editor.commit();
    }


    private List<Contact> generateContactsList(){
        List<Contact> contacts = new LinkedList<>();
        Contact contact;
        for (int i = 0; i < 20; i++) {
            contact = new Contact("Person " + i, 89510000000L + i*2498765L);
            contacts.add(contact);
        }
        return contacts;
    }

}
