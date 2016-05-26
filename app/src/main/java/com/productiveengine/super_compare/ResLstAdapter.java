package com.productiveengine.super_compare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.productiveengine.Common.cAlgorithm;
import com.productiveengine.Common.eAlgorithm;
import com.productiveengine.Model.Metric_Value;
import com.productiveengine.Model.PerformanceInfoLstItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nikolaos on 11/04/2015.
 */
public class ResLstAdapter  extends ArrayAdapter<PerformanceInfoLstItem> {
    private final Context context;
    private final ArrayList<PerformanceInfoLstItem> values;

    public ResLstAdapter(Context context, int textViewResourceId, ArrayList<PerformanceInfoLstItem> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lst_results, parent, false);
        TextView txtMetricName = (TextView) rowView.findViewById(R.id.txtMetricName);
        TextView txtMetricResult = (TextView) rowView.findViewById(R.id.txtMetricResult);

        txtMetricName.setText(values.get(position).getMetricName());
        txtMetricResult.setText(values.get(position).getMetricResult());
        return rowView;
    }

    public void convertToInfoLstItem(HashMap<eAlgorithm, Metric_Value> performance){

        PerformanceInfoLstItem performanceInfoLstItem = null;
        Metric_Value metricValue = null;
        values.clear();

        Iterator it = performance.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            metricValue = (Metric_Value) pair.getValue();

            performanceInfoLstItem = new PerformanceInfoLstItem();
            performanceInfoLstItem.setMetricName(cAlgorithm.getName((eAlgorithm) pair.getKey()));
            performanceInfoLstItem.setMetricResult(metricValue.showResult());
            values.add(performanceInfoLstItem);
        }
    }
}
