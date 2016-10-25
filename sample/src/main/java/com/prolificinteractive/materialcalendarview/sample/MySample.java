package com.prolificinteractive.materialcalendarview.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.sample.decorators.DemoMileStoneActiveDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.DemoMileStoneNodeDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.DemoTaskNodeDecorator;
import com.prolificinteractive.materialcalendarview.sample.decorators.DemoSelectorDecorator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wenhulin on 10/20/16.
 */

public class MySample extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener, OnRangeSelectedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @Bind(R.id.calendarView)
    MaterialCalendarView widget;

//    @Bind(R.id.textView)
//    TextView textView;

    private DemoTaskNodeDecorator mDemoDecorator;
    private DemoMileStoneNodeDecorator mDemoMileStoneDecorator;
    private DemoSelectorDecorator mDemoSelectorDecorator;
    private DemoMileStoneActiveDecorator mDemoMileStoneActiveDecorator;
    private Task mActiveTask;
    private List<Task> mMileStoneNodes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);

        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);

        // Test range

        Calendar instance3 = Calendar.getInstance();
        instance3.set(instance3.get(Calendar.YEAR), Calendar.OCTOBER, 5);

        //Test task node
//        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
//        widget.setOnRangeSelectedListener(this);
//          widget.selectRange();
//        mDemoDecorator = new DemoTaskNodeDecorator(this);
//        mDemoDecorator.addDate(instance3.getTime());
//        widget.addDecorator(mDemoDecorator);
//        widget.addDecorator(new DemoSelectorDecorator(this));

        // Test milestone node
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            mMileStoneNodes.add(new Task(formatter.parse("20/10/2016"), "开工\n交底"));
            mMileStoneNodes.add(new Task(formatter.parse("31/10/2016"), "隐蔽工程验收"));
            mMileStoneNodes.add(new Task(formatter.parse("15/11/2016"), "闭水验收"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        widget.setDayFormatter(new DemoMileStoneDayFormator());
        mDemoMileStoneDecorator = new DemoMileStoneNodeDecorator(this);
        mDemoSelectorDecorator = new DemoSelectorDecorator(this, false);
        mDemoMileStoneActiveDecorator = new DemoMileStoneActiveDecorator(this);
        for(Task task: mMileStoneNodes) {
            mDemoMileStoneDecorator.addDate(task.date);
            mDemoSelectorDecorator.addDate(task.date);
        }
        widget.addDecorator(mDemoSelectorDecorator);
//        widget.addDecorator(mDemoMileStoneActiveDecorator);
        widget.addDecorator(mDemoMileStoneDecorator);


        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.OCTOBER, 1);

        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        widget.state()
                .edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();

        //Setup initial text
//        textView.setText(getSelectedDatesString());

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
//        textView.setText(getSelectedDatesString());
        Log.i("Wenhui", "onDateSelected" + getSelectedDatesString());

        if (mDemoDecorator != null) {
            mDemoDecorator.setDates(widget.getSelectedDates());
            widget.invalidateDecorators();
        }

        if(mActiveTask == null) {
            Task foundTask = findTaskByDate(date);
            if (foundTask != null) {
                mActiveTask = foundTask;
                mDemoMileStoneActiveDecorator.setDate(mActiveTask.date);
            }
        } else {
            Task foundTask = findTaskByDate(date);
            if (foundTask != null) {
                mActiveTask = foundTask;
            } else {
                mActiveTask.date = date.getDate();
                mDemoMileStoneDecorator.clearDates();
                mDemoSelectorDecorator.clearDates();
                for(Task task: mMileStoneNodes) {
//                    if (task.name == mActiveTask.name) {
//                        continue;
//                    }
                    mDemoMileStoneDecorator.addDate(task.date);
                    mDemoSelectorDecorator.addDate(task.date);
                }
                mDemoMileStoneActiveDecorator.setDate(mActiveTask.date);
                widget.invalidateDecorators();
            }
        }
    }

    private Task findTaskByDate(CalendarDay date) {
        for(Task task: mMileStoneNodes) {
            if (CalendarDay.from(task.date).equals(date)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
//        textView.setText(dates.size() + " days selected");
        if (mDemoDecorator != null) {
            mDemoDecorator.setDates(widget.getSelectedDates());
            widget.invalidateDecorators();
        }
    }


    private class DemoMileStoneDayFormator implements DayFormatter {

        @NonNull
        @Override
        public String format(@NonNull CalendarDay day) {
            for(Task task: mMileStoneNodes) {
                if (CalendarDay.from(task.date).equals(day)) {
                    return task.name;
                }
            }
            return DayFormatter.DEFAULT.format(day);
        }
    }

    private static final class Task {
        Date date;
        String name;

        Task(Date date, String name) {
            this.date = date;
            this.name = name;
        }
    }
}
