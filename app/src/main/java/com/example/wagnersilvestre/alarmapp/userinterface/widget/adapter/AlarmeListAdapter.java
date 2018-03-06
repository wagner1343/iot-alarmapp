package com.example.wagnersilvestre.alarmapp.userinterface.widget.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.wagnersilvestre.alarmapp.R;
import com.example.wagnersilvestre.alarmapp.model.object.alarme.Alarme;
import com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal.MenuPrincipalPresenter;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Wagner Silvestre on 2/25/2018.
 */

public class AlarmeListAdapter extends BaseAdapter {
    private ArrayList<Alarme> alarmes;
    private Activity act;
    private MenuPrincipalPresenter presenter;

    public AlarmeListAdapter(ArrayList<Alarme> alarmes, Activity act, MenuPrincipalPresenter presenter){
        this.alarmes = alarmes;
        this.act = act;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return alarmes.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alarmes.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.alarme_item_layout, parent, false);

        TextView textViewHorario = (TextView) view.findViewById(R.id.textViewHorario);
        ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);

        final Alarme alarme = alarmes.get(position);

        if(alarme.isLigado()){
            view.setBackgroundColor(Color.argb(195, 217, 235, 240));
        } else{
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        textViewHorario.setText(
                String.format(Locale.ENGLISH, "%02d:%02d", alarme.getHoras(), alarme.getMinutos())
        );
        toggleButton.setChecked(alarme.isLigado());
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Oncheck changed for " + alarme.getId() + " to " + isChecked);
                presenter.listViewOnCheckChanged(alarme.getId(), isChecked);
            }
        });
        return view;
    }
}
