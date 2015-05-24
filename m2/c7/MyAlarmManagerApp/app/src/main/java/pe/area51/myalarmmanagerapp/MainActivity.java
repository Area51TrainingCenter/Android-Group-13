package pe.area51.myalarmmanagerapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String ALARM_ACTION = "pe.area51.myalarmmanagerapp.ALARM_FIRED";

    private EditText editTextTimeSeconds;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_button_setalarm).setOnClickListener(this);
        editTextTimeSeconds = (EditText) findViewById(R.id.activity_main_edittext_timeseconds);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_button_setalarm:
                final int timeInSeconds = Integer.parseInt(editTextTimeSeconds.getText().toString());
                setAlarm(timeInSeconds);
                break;
        }
    }

    private void setAlarm(final int timeInSeconds) {
        final Intent intent = new Intent().setAction(ALARM_ACTION);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeInSeconds * 1000, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeInSeconds * 1000, pendingIntent);
        }
    }
}
