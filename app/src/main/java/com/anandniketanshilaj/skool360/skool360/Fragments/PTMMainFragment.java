package com.anandniketanshilaj.skool360.skool360.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Activities.DashBoardActivity;
import com.anandniketanshilaj.skool360.skool360.Adapter.PTMPageAdapter;

/**
 * Created by admsandroid on 10/30/2017.
 */

public class PTMMainFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackPtm_main;
    View view;
    private TabLayout tablayout_ptm_main;
    private ViewPager viewPager;
    private Context mContext;
    PTMPageAdapter adapter;

    public PTMMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ptmmain, container, false);
        mContext = getActivity();
        init();
        setListner();
        return rootView;

    }

    public void init() {
//Initializing the tablayout

        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        btnBackPtm_main= (Button) rootView.findViewById(R.id.btnBackPtm);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        view = (View) rootView.findViewById(R.id.view);

        tablayout_ptm_main = (TabLayout) rootView.findViewById(R.id.tablayout_ptm_main);
        tablayout_ptm_main.addTab(tablayout_ptm_main.newTab().setText("Inbox"),true);
        tablayout_ptm_main.addTab(tablayout_ptm_main.newTab().setText("Sent"));
        tablayout_ptm_main.addTab(tablayout_ptm_main.newTab().setText("Create"));
        tablayout_ptm_main.setTabMode(TabLayout.MODE_FIXED);
        tablayout_ptm_main.setTabGravity(TabLayout.GRAVITY_FILL);


        adapter = new PTMPageAdapter(getFragmentManager(), tablayout_ptm_main.getTabCount());
//Adding adapter to pager
        viewPager.setAdapter(adapter);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    public void setListner() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });
        btnBackPtm_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tablayout_ptm_main));
        tablayout_ptm_main.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }
}
