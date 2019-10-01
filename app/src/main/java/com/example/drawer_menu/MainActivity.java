package com.example.drawer_menu;

import android.net.Network;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Per aggiungere l'icona di apertura e chiusura della barra laterale senza dover trascinare il touch
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer2);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView nView= (NavigationView) findViewById(R.id.nv);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Quando una voce viene selezionata dalla barra laterale:

        setupDrawerContent(nView);

    }
    public void selectItemDrawer(MenuItem menuItem){
        Fragment myFragment = null;
        Class fragmentClass;


        switch(menuItem.getItemId()){
            case R.id.home:
                fragmentClass = Home.class;
                break;
            case R.id.esplora:
                fragmentClass = Esplora.class;
                break;
            case R.id.diari:
                fragmentClass = Diari.class;
                break;
            case R.id.coupon:
                fragmentClass = Coupon.class;
                break;
            case R.id.profilo:
                fragmentClass = Profilo.class;
                break;


            default:
                fragmentClass = Home.class;
        }
        try{
            myFragment = (Fragment) fragmentClass.newInstance();

        }catch(Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawer,myFragment).commit();
        menuItem.setChecked((true));
        //Cambia il titolo della barra in base alla scelta fatta
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });

    }


    //Per restituire il valore corretto se chiudere o aprire il menu laterale
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
