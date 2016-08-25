package com.theblackraven.tgatools;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ToolsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent empfangenerIntent = this.getIntent();
        if (empfangenerIntent != null && empfangenerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            String toolInfo = empfangenerIntent.getStringExtra(Intent.EXTRA_TEXT);
            setTitle(toolInfo);
            if(toolInfo.equals("Speicherinhalt")) {
                setContentView(R.layout.activity_speichervolumen);
            }
            if(toolInfo.equals("Ventil")) {
                setContentView(R.layout.activity_ventil);
            }

            if(toolInfo.equals("Volumenstrom")) {
                setContentView(R.layout.activity_volumenstrom);
            }
            if(toolInfo.equals("Rohrtabellen")) {
                setContentView(R.layout.activity_rohrtabellen);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tool1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
