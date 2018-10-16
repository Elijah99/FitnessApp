package asus.example.com.fitnessapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ArticleActivity extends AppCompatActivity {

    private final String article = "ARTICLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        String str = getIntent().getStringExtra(article);
        TextView textView = (TextView) findViewById(R.id.article);
        textView.setText(str);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
