package com.example.homepage;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.Adapter.SectionPageAdapter;
import com.google.android.material.tabs.TabLayout;

import static com.example.homepage.R.layout.home;

public class fragment_home1 extends Fragment {

    @Nullable
    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       myFragment =  inflater.inflate(R.layout.fragment_home1,container,false);
       viewPager = myFragment.findViewById(R.id.viewPager);
       tabLayout = myFragment.findViewById(R.id.tabLayout);
       return myFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new AllFragment(),"All");
        adapter.addFragment(new OpenFragment(),"Open");
        adapter.addFragment(new InProgressFragment(),"In Progress");
        adapter.addFragment(new PastFragment(),"Past");
        viewPager.setAdapter(adapter);
    }
}

