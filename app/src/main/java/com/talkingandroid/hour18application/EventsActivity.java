package com.talkingandroid.hour18application;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import java.util.Date;
import android.provider.CalendarContract.Events;

public class EventsActivity extends Activity {
    ListView listView;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        listView = (ListView) findViewById(R.id.eventsListView);

        Intent intent = getIntent();
        long calendarId = intent.getLongExtra(CalendarsActivity.CALENDAR_ID, 1090l);
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(Events.CONTENT_URI,
                null, Events.CALENDAR_ID +"= '" + calendarId +"' AND " +
                Events.DTEND + "> " + new Date().getTime(), null, null);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {CalendarContract.Events.TITLE, CalendarContract.Events.RRULE},
                new int[] { android.R.id.text1,  android.R.id.text2,}, 0);
        listView.setAdapter(adapter);
    }
}
