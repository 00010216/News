package com.example.kcruz.gamenews.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.example.kcruz.gamenews.API.GamesAPIUtils;
import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.fragments.NewsFragment;
import com.example.kcruz.gamenews.fragments.TabFragment;
import com.example.kcruz.gamenews.utils.GameNewsSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private TabFragment frag;
    private Fragment contentFragment;
    private FragmentManager fragmentManager;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigationView);

        /*if(savedInstanceState != null) {
            if (fragmentManager.findFragmentByTag(TabFragment.ARG_ITEM_ID) != null) {
                tabFragment = (TabFragment) fragmentManager.findFragmentByTag(TabFragment.ARG_ITEM_ID);
                contentFragment = tabFragment;
            }
        }*/
        navigationView.setNavigationItemSelectedListener(this);

        GameNewsSharedPreferences.initiate(this);
        if(GameNewsSharedPreferences.hasGames())
            setGamesOptions(GameNewsSharedPreferences.getGames()); //obtiene el arreglo de string con los juegos
        requestGames();

    }

    private void requestGames() {
        Call<String[]> games = GamesAPIUtils.getApiInstanceWithAuthorization().getGames();
        games.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                if(response.isSuccessful() && response.body().length > 0){
                    Log.d("games","Response:" + response.body());
                    setGamesOptions(response.body()); //el response body devuelve el arreglo de strings
                    GameNewsSharedPreferences.getGames();
                } else {
                    Log.d("games", "onResponse: code "+response.code());
                    Log.d("games", "onResponse: message -"+response.message());
                }
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Log.d("games","onFailure:" + t.getMessage());
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

    public void setGamesOptions(String[] games){
        int itemId = 500;
        MenuItem item = navigationView.getMenu().findItem(R.id.games);
        SubMenu gamesOptions = item.getSubMenu();
        gamesOptions.clear();

        for(String game: games){
            Log.d("individual game", "setGamesOptions: game " + game);
            gamesOptions.add(0,itemId,itemId,game).setIcon(R.drawable.ic_videogame_asset_white_24dp).setCheckable(true);
            itemId ++;
        }
        gamesOptions.setGroupCheckable(0, true,true);
    }

    public void startLogInActivity(){
        //abre actividad que contiene el drawer y el menu de noticias y juegos
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
