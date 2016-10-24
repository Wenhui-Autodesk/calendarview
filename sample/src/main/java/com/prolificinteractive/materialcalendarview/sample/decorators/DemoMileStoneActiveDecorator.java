package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;

/**
 * Created by wenhulin on 10/21/16.
 */

public class DemoMileStoneActiveDecorator implements DayViewDecorator {
    private CalendarDay date;
    private Context mContext;


    public DemoMileStoneActiveDecorator(Activity context) {
        mContext = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(android.R.color.holo_blue_light)));
    }

    /**
     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
