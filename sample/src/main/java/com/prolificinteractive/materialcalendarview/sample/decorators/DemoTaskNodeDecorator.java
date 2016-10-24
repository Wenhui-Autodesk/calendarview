package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wenhulin on 10/20/16.
 */

public class DemoTaskNodeDecorator implements DayViewDecorator {
    private List<CalendarDay> dates;
    private Context mContext;

    public DemoTaskNodeDecorator(Context context) {
//        date = CalendarDay.today();
        dates = new ArrayList<>();
        mContext = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        for(CalendarDay date: dates) {
            if (day.equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
//        view.addSpan(new StyleSpan(Typeface.BOLD));
//        view.addSpan(new RelativeSizeSpan(1.0f));
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
        view.setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(android.R.color.holo_blue_light)));
    }

    /**
     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
//    public void setDates(List<Date> dates) {
//        this.dates.clear();
//        for(Date date: dates) {
//            this.dates.add(CalendarDay.from(date));
//        }
//    }

    public void setDates(List<CalendarDay> dates) {
        this.dates.clear();
        for(CalendarDay date: dates) {
            this.dates.add(date);
        }
    }

    public void addDate(@NonNull Date date) {
        this.dates.add(CalendarDay.from(date));
    }

    public void addDate(@NonNull CalendarDay date) {
        this.dates.add(date);
    }

    public void clearDates() {
        this.dates.clear();
    }
}
