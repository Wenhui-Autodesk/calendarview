package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.prolificinteractive.materialcalendarview.format.DayFormatter;

import java.util.Collections;
import java.util.List;

/**
 * Created by wenhulin on 10/24/16.
 */

public class ListViewAdapter extends BaseAdapter {
    private AdapterHelper mAdapterHelper;
    private MaterialCalendarView mcv;

    ListViewAdapter(MaterialCalendarView mcv) {
        this.mcv = mcv;
        mAdapterHelper = new AdapterHelper<MonthView>(mcv);
    }

    public void setRangeDates(CalendarDay min, CalendarDay max) {
        mAdapterHelper.setRangeDates(min, max);
        notifyDataSetChanged();
        mAdapterHelper.invalidateSelectedDates();
    }

    @Override
    public int getCount() {
        return mAdapterHelper.getCount();
    }

    @Override
    public CalendarDay getItem(int position) {
        return mAdapterHelper.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthView monthView;
//        if (convertView == null) {
            monthView = new MonthView(mcv, getItem(position), mcv.getFirstDayOfWeek());
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(parent.getWidth(),
                    mcv.getResources().getDimensionPixelSize(R.dimen.month_view_height));
            layoutParams.bottomMargin = 20;
            monthView.setLayoutParams(layoutParams);
//        } else {
//            monthView = (MonthView) convertView;
//            monthView.setData(getItem(position), mcv.getFirstDayOfWeek());
//        }

        monthView.setDayViewDecorators(mAdapterHelper.getDecoratorResult());
        monthView.setDayFormatter(mAdapterHelper.getDayFormatter());
        return monthView;
    }


    public void setSelectionColor(int color) {
        mAdapterHelper.setSelectionColor(color);
    }

    /**
     * Set the currently selected page.
     *
     * @param item Item index to select
     * @param smoothScroll True to smoothly scroll to the new item, false to transition immediately
     */
    public void setCurrentItem(int item, boolean smoothScroll) {
//        setCurrentItemInternal(item, smoothScroll, false);
    }

    public void setDecorators(List<DayViewDecorator> decorators) {
        mAdapterHelper.setDecorators(decorators);
    }

    public void invalidateDecorators() {
        mAdapterHelper.invalidateDecorators();
        notifyDataSetChanged();
    }

    public void setDateSelected(CalendarDay day, boolean selected) {
        mAdapterHelper.setDateSelected(day, selected);
    }

    public void setSelectionEnabled(boolean enabled) {
        mAdapterHelper.setSelectionEnabled(enabled);
    }

    public void clearSelections() {
        mAdapterHelper.clearSelections();
    }

    public void setDayFormatter(DayFormatter formatter) {
        mAdapterHelper.setDayFormatter(formatter);
//        notifyDataSetChanged();
    }

    @NonNull
    public List<CalendarDay> getSelectedDates() {
        return mAdapterHelper.getSelectedDates();
    }

}
