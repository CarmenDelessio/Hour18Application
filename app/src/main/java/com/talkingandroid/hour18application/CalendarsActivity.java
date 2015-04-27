package com.talkingandroid.hour18application;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class CalendarsActivity extends Activity {
    public static final String[] FIELDS = {
            Calendars._ID,
            Calendars.ACCOUNT_NAME,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
    };
    public static final String CALENDAR_ID = "com.talkingandroid.hour18application.ID";
    ListView listView;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendars);
        listView = (ListView) findViewById(R.id.listView);
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(Calendars.CONTENT_URI, FIELDS, null, null, null);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor, //cursor
                new String[] {Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME},
                new int[] { android.R.id.text1,  android.R.id.text2,},
                0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                intent.putExtra(CALENDAR_ID,id );
                startActivity(intent);

            }
        });
    }
}
