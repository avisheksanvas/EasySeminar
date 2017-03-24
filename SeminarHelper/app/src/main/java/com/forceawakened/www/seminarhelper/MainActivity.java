package com.forceawakened.www.seminarhelper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Integer where;
    private Callback callback;
    private boolean mIsBound;
    private Integer countBackPress;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set ui
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView mDrawer = (NavigationView) findViewById(R.id.navbar);
        mDrawer.setNavigationItemSelectedListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        //init
        email = getIntent().getStringExtra("EMAIL");
        countBackPress = 0;
        File outputDir = getCacheDir();
        try {
            File file = getFileStreamPath("home.txt");
            if(file.exists()) {
                File dir = getFilesDir();
                boolean deleted = file.delete();
                if(deleted){
                    System.out.println("Original Cache home.txt deleted.");
                }
            }
            File.createTempFile("home", "txt", outputDir);
            file = getFileStreamPath("query.txt");
            if(file.exists()) {
                File dir = getFilesDir();
                boolean deleted = file.delete();
                if(deleted){
                    System.out.println("Original Cache query.txt deleted.");
                }
            }
            File.createTempFile("query", "txt", outputDir);
            file = getFileStreamPath("filelist.txt");
            if(file.exists()) {
                File dir = getFilesDir();
                boolean deleted = file.delete();
                if(deleted){
                    System.out.println("Original Cache filelist.txt deleted.");
                }
            }
            File.createTempFile("filelist", "txt", outputDir);
            file = getFileStreamPath("announce.txt");
            if(file.exists()) {
                File dir = getFilesDir();
                boolean deleted = file.delete();
                if(deleted){
                    System.out.println("Original Cache announce.txt deleted.");
                }
            }
            File.createTempFile("announce", "txt", outputDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //main code
        HomePageFragment homePageFragment = new HomePageFragment();
        Fragment fragment = homePageFragment;
        callback = homePageFragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.load_content, fragment)
                .commit();
        where = 1;
        Intent intent = new Intent(MainActivity.this, SocketService.class);
        startService(intent);
        doBindService();
    }

    SocketService mBoundService ;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBoundService = ((SocketService.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBoundService = null;
        }

    };


    private void doBindService() {
        bindService(new Intent(MainActivity.this, SocketService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
        if(mBoundService!=null){
            mBoundService.IsBoundable();
        }
    }


    private void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy(){
        doUnbindService() ;
        SocketHandler.send("EXIT:");
        SocketHandler.close();
        Toast.makeText(this, "Logging Out!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        ++countBackPress;
        if(countBackPress == 1){
            Toast.makeText(this, "Press Back Again To Logout.", Toast.LENGTH_SHORT).show();
        }
        else{
            countBackPress = 0;
            super.onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    //1-> homepage, 2-> queries, 3->files
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homepage) {
            mToolbar.setTitle("Homepage");
            Fragment homepagefragment = new HomePageFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.load_content, homepagefragment)
                    .commit();
            where = 1;
        } else if (id == R.id.queries) {
            mToolbar.setTitle("Queries");
            Fragment queryfragment = new QueryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("EMAIL", email);
            queryfragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.load_content, queryfragment)
                    .commit();
            where = 2;
        }else if (id == R.id.announce) {
            mToolbar.setTitle("Announcements");
            Fragment announcefragment = new AnnounceFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.load_content, announcefragment)
                    .commit();
            where = 3;
        }
        else if (id == R.id.files) {
            mToolbar.setTitle("Get Files");
            FileListFragment listfragment = new FileListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.load_content, listfragment)
                    .commit();
            where = 4;
        }
        else if (id == R.id.about){
            mToolbar.setTitle("About");
            Fragment aboutfragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.load_content, aboutfragment)
                    .commit();
            where = 5;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public interface Callback{
        void sendMessage(String s);
    }
}
