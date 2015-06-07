package com.iuservice.udacity.androiddevelopernanodegree.project0;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.common.cache.AbstractLoadingCache;
import com.google.common.collect.FluentIterable;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    public static final String[] APPLICATION_LIST = {
            "Spotify Streamer",
            "Super Duo",
            "Build It Bigger",
            "XYX Reader",
            "Capstone"
    };

    @InjectView(R.id.buttonPanel)
    ViewGroup m_buttonPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createGui();
    }

    private void createGui() {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        generateAppListButtons();
    }

    private void generateAppListButtons() {
        for (final String applicationName : APPLICATION_LIST) {
            Button button = new Button(this);
            button.setText(applicationName);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,  String.format("This will open the '%s' application.", applicationName), Toast.LENGTH_LONG).show();
                }
            });
            m_buttonPanel.addView(button);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
