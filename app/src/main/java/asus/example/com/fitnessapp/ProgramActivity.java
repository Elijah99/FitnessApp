package asus.example.com.fitnessapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ProgramActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private final String PROGRAM="PROGRAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        //imageView.setImageResource(R.drawable.p1);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String path = getIntent().getStringExtra(PROGRAM);
        imageView.setImageResource(getResources().getIdentifier(path, "drawable", getPackageName()));
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
        InputStream inputStream;
        try {
            inputStream = getAssets().open(path);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = new String(buffer);
        textView.setText(data);
    }

}
