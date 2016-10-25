package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.format.DayFormatter;

import org.w3c.dom.Text;

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
        ViewGroup monthContainer;
        MonthView monthView;
//        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mcv.getContext());
            monthContainer = (ViewGroup) inflater.inflate(R.layout.item_month, null);
            monthView = new MonthView(mcv, getItem(position), mcv.getFirstDayOfWeek(), false);
            monthView.setId(R.id.mcv_month_view);
            ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(parent.getWidth(),
                    mcv.getResources().getDimensionPixelSize(R.dimen.month_view_height));
            monthView.setLayoutParams(layoutParams);
            monthContainer.addView(monthView);
//        } else {
//            monthContainer = (ViewGroup) convertView;
//            monthView = (MonthView) monthContainer.findViewById(R.id.mcv_month_view);
//        }

        TextView textView = (TextView) monthContainer.findViewById(R.id.item_title);
        textView.setText("2016年" + (10 + position) + "月");

//        monthView.setData(getItem(position), mcv.getFirstDayOfWeek()); //TODO Do more optimize
        monthView.setContentDescription(mcv.getCalendarContentDescription());
        monthView.setSelectionEnabled(mAdapterHelper.getSelectionEnabled());
        monthView.setShowOtherDates(mAdapterHelper.getShowOtherDates());
        monthView.setMinimumDate(mAdapterHelper.getMininumDate());
        monthView.setMaximumDate(mAdapterHelper.getMaximumDate());
        monthView.setSelectedDates(mAdapterHelper.getSelectedDates());
        monthView.setDayViewDecorators(mAdapterHelper.getDecoratorResult());
        monthView.setDayFormatter(mAdapterHelper.getDayFormatter());
        monthView.setShowWeekDays(false);

        return monthContainer;
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
