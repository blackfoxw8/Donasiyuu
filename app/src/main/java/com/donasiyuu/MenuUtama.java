package com.donasiyuu;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.donasiyuu.Fragment.fragment_about;
import com.donasiyuu.Fragment.fragment_bantukami;
import com.donasiyuu.Fragment.fragment_home;

public class MenuUtama extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        loadfragment(new fragment_home());
    }

    private boolean loadfragment(Fragment fragment)
    {
        if(fragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, fragment).commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home:
                fragment = new fragment_home();
                break;
            case R.id.navigation_bantukami:
                fragment = new fragment_bantukami();
                break;
            case R.id.navigation_about:
                fragment = new fragment_about();
                break;
        }


        return loadfragment(fragment);
    }
}
