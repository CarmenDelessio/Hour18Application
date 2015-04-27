package com.talkingandroid.hour18application;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.provider.CalendarContract.Events;

import java.util.Calendar;


public class MainActivity extends Activity {
    String[] choices;
    ListView choicesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resources = getResources();
        choices = resources.getStringArray(R.array.choices_array);
        choicesListView = (ListView) findViewById(R.id.listViewMain);
        choicesListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, choices));
        choicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, CalendarsActivity.class));
                        break;
                    case 1:
                        Calendar beginTime = Calendar.getInstance();
                        beginTime.set(2015, 11, 31, 23, 30);
                        Calendar endTime = Calendar.getInstance();
                        endTime.set(2016, 0, 1, 2, 30);
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(Events.TITLE, "New Years Party")
                                .putExtra(Events.DESCRIPTION, "Have fun")
                                .putExtra(Events.EVENT_LOCATION, "Our house")
                                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
                                .putExtra(Intent.EXTRA_EMAIL, "carmendelessio@gmail.com");
                        startActivity(intent);
                        break;
//                    case 2:
//                        startActivity(new Intent(MainActivity.this, GeneratedSettingsActivity.class));
//                        break;
                }

            }
        });
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
