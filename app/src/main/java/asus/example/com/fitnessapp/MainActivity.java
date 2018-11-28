package asus.example.com.fitnessapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Toolbar");
        Fragment fragment = new ProgramsFragment();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        FragmentTransaction ft;
        switch (item.getItemId()) {
            case R.id.programs:
                fragment = new ProgramsFragment();
                break;
            case R.id.indications:
                fragment = new IndicationsFragment();
                break;
            case R.id.calculator:
                fragment = new CalculatorFragment();
                break;
            case R.id.articles:
                fragment = new ArticlesFragment();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        return true;
    }


}