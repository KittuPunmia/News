package com.kittu.news;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.orientation;

public class MainActivity extends AppCompatActivity {
String TAG="Success";
  //  Button btn;
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationview;
    RecyclerView recyclerview;
    int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         setUpToolbar();
        getData();
        recyclerview=(RecyclerView) findViewById(R.id.newsList);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview.getContext(),
                linearLayoutManager.getOrientation());
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.addItemDecoration(dividerItemDecoration);
        setRequestedOrientation(orientation);


        navigationview= (NavigationView) findViewById(R.id.navigation_menu);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "HOME", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_LatestNews:
                        Toast.makeText(MainActivity.this, "LatestNews", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_aboutuse:
                        Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });

    }
    private void setUpToolbar(){
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        toolBar= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp.xml);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    public void getData()
    {
        Call<NewsList> newsListCall=ApiClient.getApiClient().getNews();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList list=response.body();
                recyclerview.setAdapter(new NewsAdapter(MainActivity.this,list.getArticles()));
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
