package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;

import com.prolificinteractive.materialcalendarview.format.DayFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.util.List;

/**
 * Created by wenhulin on 10/30/16.
 */

public interface IAdapter {
    void clearSelections();

    void setRangeDates(CalendarDay min, CalendarDay max);

    void setSelectionEnabled(boolean enabled);
    void setSelectionColor(int color);
    void setDateTextAppearance(int taId);
    void setWeekDayTextAppearance(int resourceId);
    void setDateSelected(CalendarDay day, boolean selected);
    void setShowOtherDates(@MaterialCalendarView.ShowOtherDates int showFlags);
    void setWeekDayFormatter(WeekDayFormatter formatter);
    void setDayFormatter(DayFormatter formatter);
    void setTitleFormatter(@NonNull TitleFormatter titleFormatter);
    void setDecorators(List<DayViewDecorator> decorators);
    void invalidateDecorators();

    int getIndexForDay(CalendarDay day);
    int getDateTextAppearance();
    @MaterialCalendarView.ShowOtherDates
    int getShowOtherDates();
    int getWeekDayTextAppearance();
    List<CalendarDay> getSelectedDates();



}
