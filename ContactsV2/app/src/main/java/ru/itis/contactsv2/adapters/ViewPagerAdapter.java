package ru.itis.contactsv2.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.itis.contactsv2.fragments.RecyclerViewFragment;
import ru.itis.contactsv2.interfaces.Updatable;

/**
 * Created by ilmaz on 25.10.16.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return RecyclerViewFragment.newInstance(false);
            case 1 :
                return RecyclerViewFragment.newInstance(true);
            default:
                return RecyclerViewFragment.newInstance(false);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Contacts";
            default:
                return "Deleted";
        }
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof Updatable){
            ((Updatable) object).updateAdapter();
        }
        return super.getItemPosition(object);
    }
}
