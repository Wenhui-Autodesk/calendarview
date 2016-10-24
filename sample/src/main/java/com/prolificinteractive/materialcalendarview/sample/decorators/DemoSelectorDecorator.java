package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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

public class DemoSelectorDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private List<CalendarDay> dates = new ArrayList<>();

    public DemoSelectorDecorator(Activity context, boolean isMileStone) {
        if (isMileStone) {
            drawable = context.getResources().getDrawable(R.drawable.demo_milestone_selector);
        } else  {
            drawable = context.getResources().getDrawable(R.drawable.demo_selector);
            Log.i("Wenhui", "DemoSelectorDecorator =" + drawable.toString());
        }

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
//        Log.i("Wenhui", "DemoSelectorDecorator shouldDecorate");
        for(CalendarDay date: dates) {
            if (day.equals(date)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
        Log.i("Wenhui", "DemoSelectorDecorator decorate");
    }

    public void setExcludeDates(List<CalendarDay> dates) {
        this.dates.clear();
        for(CalendarDay date: dates) {
            this.dates.add(date);
        }
    }

    public void addDate(@NonNull Date date) {
        this.dates.add(CalendarDay.from(date));
    }

    public void clearDates() {
        this.dates.clear();
    }
}
