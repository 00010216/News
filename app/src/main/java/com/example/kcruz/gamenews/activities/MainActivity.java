package com.example.kcruz.gamenews.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.fragments.NewsFragment;
import com.example.kcruz.gamenews.fragments.TabFragment;
import com.example.kcruz.gamenews.models.News;
import com.example.kcruz.gamenews.utils.GameNewsSharedPreferences;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private TabFragment frag;
    private Fragment contentFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setElevation(0);

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.navigationView);

        /*if(savedInstanceState != null) {
            if (fragmentManager.findFragmentByTag(TabFragment.ARG_ITEM_ID) != null) {
                tabFragment = (TabFragment) fragmentManager.findFragmentByTag(TabFragment.ARG_ITEM_ID);
                contentFragment = tabFragment;
            }
        }*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                item.setChecked(true);

                drawer.closeDrawers();

                switch(item.getItemId()){
                    case R.id.news:
                        NewsFragment news = new NewsFragment();
                        startFragment(R.string.news, news);
                        break;
                    case R.id.logout:
                        GameNewsSharedPreferences.logOut();
                        startLogInActivity();
                        finish();
                    case R.id.game_league_of_legends:
                        frag = new TabFragment();
                        startFragment(R.string.league_of_legends, frag);
                        break;
                    case R.id.game_dota:
                        frag = new TabFragment();
                        startFragment(R.string.dota, frag);
                        break;
                    case  R.id.game_cs_go:
                        frag = new TabFragment();
                        startFragment(R.string.cs_go, frag);
                        break;
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void setTitle(int resource){
        getSupportActionBar().setTitle(getResources().getString(resource));
    }


    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //Crea el fragment con los tabs y el respectivo titulo del juego en el action bar
    public void startFragment(int title, Fragment frag){
        setTitle(title);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, frag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.app_bar_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startLogInActivity(){
        //abre actividad que contiene el drawer y el menu de noticias y juegos
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }

}
