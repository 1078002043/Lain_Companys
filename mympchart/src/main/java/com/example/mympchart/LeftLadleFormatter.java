package com.example.mympchart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class LeftLadleFormatter extends ValueFormatter {

    private final DecimalFormat mFormat;
    private String suffix;

    public LeftLadleFormatter(String suffix) {
        mFormat = new DecimalFormat("###,###,###,##0.0");
        this.suffix = suffix;
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + suffix;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return mFormat.format(value) + suffix;
//        if (axis instanceof XAxis) {
//            return mFormat.format(value);
//        } else if (value > 0) {
//            return mFormat.format(value) + suffix;
//        } else {
//            return mFormat.format(value);
//        }
    }

}
