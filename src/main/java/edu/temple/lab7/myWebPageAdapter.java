package edu.temple.lab7;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class myWebPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<myWebFragment> myWebFragmentArray;

    public myWebPageAdapter(FragmentManager fm) {
        super(fm);
        myWebFragmentArray = new ArrayList<>();

    }

    public Fragment getItem(int currentFragmentPos) {
        return myWebFragmentArray.get(currentFragmentPos);
    }

    @Override
    public int getCount() {
        return myWebFragmentArray.size();
    }

    public void createAWebFragment() {
        myWebFragmentArray.add(new myWebFragment());
        notifyDataSetChanged(); // Use when .add of fragment is used to add fragment to an array
    }

    public String something() {
        String some_string = "Opening Web page";
        return some_string;
    }
}
