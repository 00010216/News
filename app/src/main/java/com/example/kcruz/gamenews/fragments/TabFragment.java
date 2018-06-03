package com.example.kcruz.gamenews.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.adapters.ViewPagerAdapter;


public class TabFragment extends Fragment {

    public static final String ARG_ITEM_ID = "tab_game_list";

    private View view;
    private TabLayout main_tab;
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;

    //declaracion del fragmentos que se ocuparan en los tabs
    private AboutGameFragment aboutGameFragment;
    private TopPlayersListFragment topPlayersListFragment;
    private ImagesFragment imagesFragment;

    FragmentManager fm;


    public TabFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, container, false);
        prepareTabs();
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void prepareTabs(){
        main_tab = view.findViewById(R.id.tablayout);
        viewpager = view.findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getFragmentManager());

        aboutGameFragment = new AboutGameFragment();
        topPlayersListFragment = new TopPlayersListFragment();
        imagesFragment = new ImagesFragment();

        adapter.addFragment(aboutGameFragment, getResources().getString(R.string.about));
        adapter.addFragment(topPlayersListFragment, getResources().getString(R.string.top_players));
        adapter.addFragment(imagesFragment, getResources().getString(R.string.images) );

        viewpager.setAdapter(adapter);
        main_tab.setupWithViewPager(viewpager);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = adapter.getFragment(position);
                if(fragment != null) fragment.onResume();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
