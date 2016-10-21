package ru.itis.contacts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  MyAdapter.onSomeEventListener, MyDialog.dialogOnClickListener{

    private List<Contact> contacts;
    private List<Contact> deletedContacts;
    private RecyclerViewFragment fragment1;
    private RecyclerViewFragment fragment2;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = getContacts();
        deletedContacts = getDeletedContacts();

        List<Fragment> fragments = new LinkedList<>();
        fragment1 = RecyclerViewFragment.newInstance(contacts);
        fragments.add(fragment1);
        fragment2 = RecyclerViewFragment.newInstance(deletedContacts);
        fragments.add(fragment2);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(pagerAdapter);

//        Fragment fragment = new RecyclerViewFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("contacts", (Serializable) contacts);
//        bundle.putSerializable("delContacts", (Serializable) deletedContacts);
//        fragment.setArguments(bundle);
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fl_list, fragment, RecyclerViewFragment.class.getName())
//                .commit();

    }

    private List<Contact> fillContacts(){
        List<Contact> contacts = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            contacts.add(new Contact(89510660000L + i * 20 + i * 8));
        }
        return contacts;
    }

    @Override
    public void onClick(Contact contact) {
        Fragment fragment = new InformFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);
        fragment.setArguments(bundle);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_list, fragment, InformFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onLongClick(Contact contact, MyAdapter adapter) {
        MyDialog dialog = new MyDialog();
        dialog.setInformation(contact, adapter);
        dialog.show(getFragmentManager(), MyDialog.class.getName());
    }

    public void saveContacts(List<Contact> contacts, List<Contact> deletedContacts){
        SharedPreferences preferences = getSharedPreferences("contacts", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        JSONArray array = new JSONArray();
        for (Contact k :
                contacts) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("number", k.getNumber());
                obj.put("isDeleted", k.isDeleted());
                array.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        editor.putString("contacts", array.toString());
        array = new JSONArray();
        for (Contact k :
                deletedContacts) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("number", k.getNumber());
                obj.put("isDeleted", k.isDeleted());
                array.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        editor.putString("deletedContacts", array.toString());
        editor.commit();
    }

    public List<Contact> getContacts(){
        SharedPreferences preferences = getSharedPreferences("contacts", MODE_PRIVATE);
        String temp = preferences.getString("contacts", "");
        if(temp.length() == 0){
            return fillContacts();
        } else {
            try {
                JSONArray array = new JSONArray(temp);
                List<Contact> contacts = new LinkedList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Contact contact = new Contact(obj.getLong("number"), obj.getBoolean("isDeleted"));
                    contacts.add(contact);
                }
                return contacts;
            } catch (JSONException e) {
                e.printStackTrace();
                return fillContacts();
            }
        }
    }

    public List<Contact> getDeletedContacts(){
        SharedPreferences preferences = getSharedPreferences("contacts", MODE_PRIVATE);
        String temp = preferences.getString("deletedContacts", "");
        if(temp.length() == 0){
            return new LinkedList<>();
        } else {
            try {
                JSONArray array = new JSONArray(temp);
                List<Contact> contacts = new LinkedList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Contact contact = new Contact(obj.getLong("number"), obj.getBoolean("isDeleted"));
                    contacts.add(contact);
                }
                return contacts;
            } catch (JSONException e) {
                e.printStackTrace();
                return new LinkedList<>();
            }
        }
    }

    @Override
    public void onYesClick(Contact contact, MyAdapter adapter) {
        if(contact.isDeleted()) {
            deletedContacts.remove(contact);
            contact.setDeleted(false);
            contacts.add(contact);
        } else {
            contacts.remove(contact);
            contact.setDeleted(true);
            deletedContacts.add(contact);
        }
        saveContacts(contacts, deletedContacts);
        adapter.notifyDataSetChanged();
        if(fragment1.myAdapter != null){
            fragment1.myAdapter.notifyDataSetChanged();
        }
        if(fragment2.myAdapter != null){
            fragment2.myAdapter.notifyDataSetChanged();
        }
    }
}
