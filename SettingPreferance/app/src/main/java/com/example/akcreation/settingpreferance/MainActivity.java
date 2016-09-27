package com.example.akcreation.settingpreferance;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
  private static final int RESULT_SETTINGS=1;
    TextView settings;
    Button setSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSettings = (Button)findViewById(R.id.button_settings);
        setSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, UserSettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
            }
        });
        settings = (TextView)findViewById(R.id.text_view_settings);

                showUserSettings();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_settings:
                Intent i =new Intent(this, UserSettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case RESULT_SETTINGS:
                showUserSettings();
                break;
        }
    }

    private void showUserSettings(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        StringBuilder builder = new StringBuilder();

        builder.append("/n Password: " + sharedPrefs.getString("prefPassword", "NULL"));

        builder.append("/n Screen Lock: " + sharedPrefs.getBoolean("prefScreenLock", true));

        builder.append("/n Reminder : " + sharedPrefs.getString("prefReminder", "NULL"));

        settings.setText(builder.toString());


    }
}
