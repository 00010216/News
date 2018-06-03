package com.example.kcruz.gamenews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>(); //lista de los fragmentosque se mostraran en los tabs
    private final  List<String> fragmentTitles = new ArrayList<>(); //lista de los titulos que pareceran en los tabs
    private FragmentManager fragmentManager;
    private final Map<Integer, String> fragmentTags;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        fragmentTags = new HashMap<Integer, String>(); //se incializa el map
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position); //se obtiene el fragmento seleccionado
    }

    @Override
    public int getCount() {
        return fragmentTitles.size(); //se obtiene el numero de tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position); //se obtiene la posicion del titulo seleccionado
    }


    public void addFragment(Fragment f, String title){
        //metodo que se usara para ir agregado los nombres del fragmento y el framgento correspondiente a las listas
        fragmentList.add(f);
        fragmentTitles.add(title);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //se crea una instancia del fragmento seleccionado
        Object obj = super.instantiateItem(container, position);
        if(obj instanceof Fragment){
            Fragment f = (Fragment) obj;
            fragmentTags.put(position, f.getTag());
        }
        return obj;
    }

    public Fragment getFragment(int position){
        //obtiene el fragmento segun la posicion
        String tag = fragmentTags.get(position);
        if(tag == null) return  null;
        return fragmentManager.findFragmentByTag(tag);
    }
}
