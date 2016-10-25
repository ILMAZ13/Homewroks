package ru.itis.contactsv2;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ru.itis.contactsv2.adapters.ViewPagerAdapter;

public class ContactsActivity extends AppCompatActivity{

    private ViewPager pager;
    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }

    public FragmentStatePagerAdapter getPagerAdapter(){
        return pagerAdapter;
    }
}
