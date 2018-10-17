package asus.example.com.fitnessapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class ArticleActivity extends AppCompatActivity {

    private final String article = "ARTICLE";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        String path = getIntent().getStringExtra(article);
        textView = (TextView) findViewById(R.id.article);
        readFromFile(path);
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

    private void readFromFile(String path){
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(path);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = new String(buffer);
        textView.setText(data);

    }
}
