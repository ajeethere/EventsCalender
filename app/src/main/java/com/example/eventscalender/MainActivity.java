package com.example.eventscalender;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    List<CalenderObj> daysListEvent = new ArrayList<>();
    List<CalenderObj> daysListEventAll = new ArrayList<>();

    boolean absent = false, present = false, holiday = false;

    int firstdayofweek;
    String[] years = {"2018","2019", "2020", "2021","2025"};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    Spinner yearSpinner, monthSpinner;
    GridView gridview;
    CalendarAdapter ca;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daysListEventAll.add(new CalenderObj(21, "2020", "January", "p"));// here event type is "p" for present, "a" for absent, "h" for holiday, three types of events can be added if you want to add more type of events just understand the code and add how many types of events you want to add
        daysListEventAll.add(new CalenderObj(12, "2019", "February", "a"));
        daysListEventAll.add(new CalenderObj(31, "2019", "March", "h"));
        daysListEventAll.add(new CalenderObj(15, "2019", "April", "p"));
        daysListEventAll.add(new CalenderObj(16, "2019", "May", "p"));
        daysListEventAll.add(new CalenderObj(11, "2020", "January", "p"));
        daysListEventAll.add(new CalenderObj(17, "2019", "January", "a"));
        daysListEventAll.add(new CalenderObj(27, "2019", "February", "h"));
        daysListEventAll.add(new CalenderObj(25, "2019", "March", "p"));
        daysListEventAll.add(new CalenderObj(3, "2019", "April", "p"));

        daysListEventAll.add(new CalenderObj(16, "2020", "February", "p"));
        daysListEventAll.add(new CalenderObj(19, "2020", "January", "a"));
        daysListEventAll.add(new CalenderObj(21, "2019", "February", "h"));
        daysListEventAll.add(new CalenderObj(9, "2019", "March", "p"));
        daysListEventAll.add(new CalenderObj(6, "2019", "April", "p"));
        daysListEventAll.add(new CalenderObj(13, "2020", "January", "p"));
        daysListEventAll.add(new CalenderObj(8, "2019", "January", "a"));
        daysListEventAll.add(new CalenderObj(29, "2019", "April", "h"));
        daysListEventAll.add(new CalenderObj(27, "2019", "January", "p"));
        daysListEventAll.add(new CalenderObj(6, "2025", "February", "p"));

        daysListEventAll.add(new CalenderObj(16, "2021", "March", "p"));
        daysListEventAll.add(new CalenderObj(19, "2018", "February", "a"));
        daysListEventAll.add(new CalenderObj(21, "2019", "May", "h"));

        yearSpinner = (Spinner) findViewById(R.id.year_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        yearSpinner.setAdapter(adapter);
        yearSpinner.setOnItemSelectedListener(this);

        monthSpinner = (Spinner) findViewById(R.id.month_spinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, months);
        monthSpinner.setAdapter(adapter1);
        monthSpinner.setOnItemSelectedListener(this);

        gridview = (GridView) findViewById(R.id.gridview);
        ca = new CalendarAdapter(this);
        gridview.setAdapter(ca);


        createCalendar(2018, Calendar.JUNE);
    }

    public void createCalendar(int year, int month) {

        int day = 1;
        for (int i = 0; i < texts.length; i++)
            texts[i] = "";
        GregorianCalendar cal = new GregorianCalendar(year, month, day);
        int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        for (int j = 0; j < days.length; j++)
            texts[j] = days[j];
        for (int i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            texts[((row + 1) * 7) + column] = String.valueOf(i);

        }
        ca.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        daysListEvent.clear();
        for (int k=0;k<daysListEventAll.size();k++) {
            if (daysListEventAll.get(k).getYear().equals(yearSpinner.getSelectedItem().toString())) {
                if (daysListEventAll.get(k).getMonth().equals(monthSpinner.getSelectedItem().toString())) {
                    daysListEvent.add(new CalenderObj(daysListEventAll.get(k).getDay(), daysListEventAll.get(k).getYear(), daysListEventAll.get(k).getMonth(), daysListEventAll.get(k).getEventType()));
                }
            }
        }
        createCalendar(Integer.parseInt((String) yearSpinner.getSelectedItem()), monthSpinner.getSelectedItemPosition());

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {


    }

    private String[] texts = new String[49];

    private class CalendarAdapter extends BaseAdapter {
        Button btn;
        private Context mContext;


        public CalendarAdapter(Context c) {
            mContext = c;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
                btn = new Button(mContext);

                btn.setLayoutParams(new GridView.LayoutParams(display.getWidth() / 7, ViewGroup.LayoutParams.WRAP_CONTENT));

            } else {
                btn = (Button) convertView;
            }
            if (texts[position].equals("")) {

                btn.setVisibility(View.GONE);
                btn.setFocusable(false);
                btn.setClickable(false);
            } else {
                btn.setVisibility(View.VISIBLE);
                btn.setFocusable(true);
            }
            btn.setText(texts[position]);


            btn.setBackgroundColor(Color.parseColor("#989EFE"));// Background color for calender
            for (int i = 0; i < daysListEvent.size(); i++) {
                if (yearSpinner.getSelectedItem().equals(daysListEvent.get(i).getYear())) {
                    if (monthSpinner.getSelectedItem().toString().equals(daysListEvent.get(i).getMonth())) {
                        if (texts[position].equals(daysListEvent.get(i).getDay()+"")) {
                            if (daysListEvent.get(i).getEventType().equals("p")) {
                                btn.setBackgroundColor(Color.parseColor("#0C9C07")); // background color for present
                            } else if (daysListEvent.get(i).getEventType().equals("a")) {
                                btn.setBackgroundColor(Color.parseColor("#9C0722"));// background color for absent
                            } else if (daysListEvent.get(i).getEventType().equals("h")) {
                                btn.setBackgroundColor(Color.parseColor("#9C0791"));// background color for holiday
                            }
                        }
                    } else {
                        btn.setBackgroundColor(Color.parseColor("#989EFE"));
                    }
                } else {
                    btn.setBackgroundColor(Color.parseColor("#989EFE"));
                }
            }


            if (position == 7 || position == 14 || position == 21 || position == 28 || position == 35 || position == 42) {
                btn.setBackgroundColor(Color.parseColor("#07A796")); // background color for sundays
            }

            return btn;
        }

        @Override
        public int getCount() {
            return texts.length;
        }

    }
}