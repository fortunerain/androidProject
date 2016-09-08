package com.android.kdc.minhonoh.nmhproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BlankActivity extends Activity {
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        intent = getIntent();
        String color = intent.getStringExtra("color");
        View v = findViewById(R.id.blankLayout);
        v.setBackgroundColor(Color.parseColor(color));


        //intent2 = new Intent(getApplicationContext(),MainActivity.class);
    }

    public void changeMainBackColor(View view){
        switch (view.getId()) {
            case R.id.img_1:
                intent.putExtra("value", "#01A7B0");
                break;
            case R.id.img_2:
            	intent.putExtra("value", "#2F00FF");
                break;
            case R.id.img_3:
            	intent.putExtra("value", "#3AAD00");
                break;
            case R.id.img_4:
            	intent.putExtra("value", "#FA68CA");
                break;
            default:
                break;
        }

        this.setResult(100, intent);
        finish();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
