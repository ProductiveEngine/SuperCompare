package com.productiveengine.super_compare;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.productiveengine.Common.DecimalDigitsInputFilter;
import com.productiveengine.Common.eBenchmarkType;
import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.BenchmarkSettings;

public class BenchmarkSettingsUI extends Fragment {

    private ListView lstSettingsView;
    private List<BenchmarkSettings> benchSettings;
    private ArrayAdapter<BenchmarkSettings> listAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        //List<AlgorithmSettings> s = AlgorithmSettings.listAll(AlgorithmSettings.class);
        // Find the ListView resource.
        lstSettingsView = (ListView) v.findViewById(R.id.lstSettings);

        // When item is tapped, toggle checked properties of CheckBox and Planet.
        lstSettingsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item,
                                    int position, long id) {
                BenchmarkSettings benchSettings = listAdapter.getItem(position);
               // planet.toggleChecked();
               // PlanetViewHolder viewHolder = (PlanetViewHolder) item.getTag();
               // viewHolder.getCheckBox().setChecked(planet.isChecked());
            }
        });

        // Create and populate benchSettings.
        benchSettings = (List<BenchmarkSettings>) getActivity().getLastNonConfigurationInstance() ;
        if ( benchSettings == null ) {
            benchSettings = BenchmarkSettings.loadSettings();
        }
        ArrayList<BenchmarkSettings> benchSettingsLst = new ArrayList<BenchmarkSettings>();
        benchSettingsLst.addAll(benchSettings);

        // Set our custom array adapter as the ListView's adapter.
        listAdapter = new SettingsArrayAdapter(this.getActivity(), benchSettingsLst);
        lstSettingsView.setAdapter(listAdapter);

        return v;
    }

    /** Holds child views for one row. */
    private static class SettingsViewHolder {
        private TextView txtAlgoname ;
        private CheckBox chkTypeI, chkTypeII;
        private EditText txtWeight, txtTime;

        public SettingsViewHolder() {}

        public SettingsViewHolder(TextView txtAlgoname, CheckBox chkTypeI, EditText txtWeight, CheckBox chkTypeII, EditText txtTime) {
            this.txtAlgoname = txtAlgoname ;
            this.chkTypeI = chkTypeI ;
            this.txtWeight = txtWeight;
            this.chkTypeII = chkTypeII ;
            this.txtTime = txtTime;
        }

        public TextView getTxtAlgoname() {
            return txtAlgoname;
        }

        public void setTxtAlgoname(TextView txtAlgoname) {
            this.txtAlgoname = txtAlgoname;
        }

        public CheckBox getChkTypeI() {
            return chkTypeI;
        }

        public void setChkTypeI(CheckBox chkTypeI) {
            this.chkTypeI = chkTypeI;
        }

        public CheckBox getChkTypeII() {
            return chkTypeII;
        }

        public void setChkTypeII(CheckBox chkTypeII) {
            this.chkTypeII = chkTypeII;
        }

        public EditText getTxtWeight() {
            return txtWeight;
        }

        public void setTxtWeight(EditText txtWeight) {
            this.txtWeight = txtWeight;
        }

        public EditText getTxtTime() {
            return txtTime;
        }

        public void setTxtTime(EditText txtTime) {
            this.txtTime = txtTime;
        }
    }

    /** Custom adapter for displaying an array of BenchmarkSettings objects. */
    private static class SettingsArrayAdapter extends ArrayAdapter<BenchmarkSettings> {

        private LayoutInflater inflater;

        public SettingsArrayAdapter(Context context, List<BenchmarkSettings> planetList) {
            super( context, R.layout.settings_row, R.id.rowTextView, planetList );
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(context) ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Planet to display
            BenchmarkSettings planet = (BenchmarkSettings) this.getItem( position );

            // The child views in each row.
            TextView txtAlgoname ;
            CheckBox chkTypeI, chkTypeII;
            final EditText txtWeight, txtTime;

            // Create a new row view
            if ( convertView == null ) {
                convertView = inflater.inflate(R.layout.settings_row, null);

                // Find the child views.
                txtAlgoname = (TextView) convertView.findViewById( R.id.lblAlgoname);
                chkTypeI = (CheckBox) convertView.findViewById( R.id.chkTypeI);
                txtWeight = (EditText) convertView.findViewById( R.id.txtWeight);
                chkTypeII = (CheckBox) convertView.findViewById( R.id.chkTypeII);
                txtTime = (EditText) convertView.findViewById( R.id.txtTime);
                //-----------------------------------------------------------------------
                // Optimization: Tag the row with it's child views, so we don't have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new SettingsViewHolder(txtAlgoname, chkTypeI, txtWeight, chkTypeII, txtTime));

                //-------------------------------------------------------------------
                // If CheckBox is toggled, update the planet it is tagged with.
                chkTypeI.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        BenchmarkSettings setting = (BenchmarkSettings) cb.getTag();

                        for (AlgorithmSettings settings : setting.getAlgoSettigns()) {
                            if (settings.getBenchmarkType() == eBenchmarkType.TYPE_I.ordinal()) {
                                settings.setIsSelected(cb.isChecked());
                                settings.save();
                            }
                        }
                    }
                });

                txtWeight.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        if ( s.length() > 0) {
                            BenchmarkSettings setting = (BenchmarkSettings) txtWeight.getTag();

                            for (AlgorithmSettings settings : setting.getAlgoSettigns()) {
                                if (settings.getBenchmarkType() == eBenchmarkType.TYPE_I.ordinal()) {
                                    settings.setParameter(Double.parseDouble(txtWeight.getText().toString()));
                                    settings.save();
                                }
                            }
                        }
                    }
                });

                chkTypeII.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        BenchmarkSettings setting = (BenchmarkSettings) cb.getTag();

                        for (AlgorithmSettings settings : setting.getAlgoSettigns()) {
                            if (settings.getBenchmarkType() == eBenchmarkType.TYPE_II.ordinal()) {
                                settings.setIsSelected(cb.isChecked());
                                settings.save();
                            }
                        }
                    }
                });

                txtTime.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() > 0) {
                            BenchmarkSettings setting = (BenchmarkSettings) txtTime.getTag();

                            for (AlgorithmSettings settings : setting.getAlgoSettigns()) {
                                if (settings.getBenchmarkType() == eBenchmarkType.TYPE_II.ordinal()) {
                                    settings.setParameter(Double.parseDouble(txtTime.getText().toString()));
                                    settings.save();
                                }
                            }
                        }
                    }
                });
                //-------------------------------------------------------------------
            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call findViewById().
                SettingsViewHolder viewHolder = (SettingsViewHolder) convertView.getTag();
                txtAlgoname = viewHolder.getTxtAlgoname();
                chkTypeI = viewHolder.getChkTypeI();
                txtWeight = viewHolder.getTxtWeight();
                chkTypeII = viewHolder.getChkTypeII();
                txtTime = viewHolder.getTxtTime();
            }

            // Tag the CheckBox with the Planet it is displaying, so that we can
            // access the planet in onClick() when the CheckBox is toggled.
            chkTypeI.setTag(planet);
            txtWeight.setTag(planet);
            chkTypeII.setTag(planet);
            txtTime.setTag(planet);

            // Display planet data
            txtAlgoname.setText(planet.getAlgoName());
            chkTypeI.setChecked(planet.getAlgoSettigns().get(0).isSelected());
            txtWeight.setText(planet.getAlgoSettigns().get(0).getParameter() + "");
            txtWeight.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3, 2)});
            chkTypeII.setChecked(planet.getAlgoSettigns().get(1).isSelected());
            txtTime.setText(planet.getAlgoSettigns().get(1).getParameter() + "");
            txtTime.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3, 2)});

            return convertView;
        }

    }

    public Object onRetainNonConfigurationInstance() {
        return benchSettings;
    }
}