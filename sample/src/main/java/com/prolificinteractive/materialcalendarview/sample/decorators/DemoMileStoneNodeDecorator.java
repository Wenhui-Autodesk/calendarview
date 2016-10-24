package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.sample.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wenhulin on 10/20/16.
 */

public class DemoMileStoneNodeDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private List<CalendarDay> dates;
    private Context mContext;

    public DemoMileStoneNodeDecorator(Activity context) {
        mContext = context;
        drawable = context.getResources().getDrawable(R.drawable.demo_milestone_selector);
        Log.i("Wenhui", "DemoMileStoneNodeDecorator =" + drawable.toString());
        dates = new ArrayList<>();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
//        Log.i("Wenhui", "DemoMileStoneNodeDecorator shouldDecorate");
        for(CalendarDay date: dates) {
            if (day.equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLACK));
//        view.setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(android.R.color.darker_gray)));
        view.setSelectionDrawable(drawable);
//        view.setBackgroundDrawable(drawable);
        Log.i("Wenhui", "DemoMileStoneNodeDecorator decorate");
    }

    public void setDates(List<Date> dates) {
        this.dates.clear();
        for(Date date: dates) {
            this.dates.add(CalendarDay.from(date));
        }
    }

    public void addDate(@NonNull Date date) {
        this.dates.add(CalendarDay.from(date));
    }

    public void clearDates() {
        this.dates.clear();
    }
}
