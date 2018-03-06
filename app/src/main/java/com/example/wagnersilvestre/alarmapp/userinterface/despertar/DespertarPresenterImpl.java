package com.example.wagnersilvestre.alarmapp.userinterface.despertar;

import android.media.MediaPlayer;

import com.example.wagnersilvestre.alarmapp.R;
import com.example.wagnersilvestre.alarmapp.model.object.alarme.AlarmeModel;
import com.example.wagnersilvestre.alarmapp.model.object.alarme.AlarmeModelImpl;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public class DespertarPresenterImpl implements DespertarPresenter {
    private DespertarView view;
    private MediaPlayer mp;
    private AlarmeModel alarmeModel;

    public DespertarPresenterImpl(DespertarView view){
        this.view = view;
        alarmeModel = new AlarmeModelImpl(view.getViewActivity().getBaseContext());
        Calendar now = Calendar.getInstance();

        mp = MediaPlayer.create(view.getViewActivity().getBaseContext(), R.raw.swang);
        mp.start();

        int id = view.getViewActivity().getIntent().getIntExtra("alarme_id", 0);
        //view.mostrarId(String.valueOf(id));
        view.mostrarHorario(String.format(Locale.ENGLISH, "%02d:%02d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE)));

        System.out.println("Alarme " + id + " despertou");
        remarcarAlarme(id);
    }

    private void remarcarAlarme(int id){
        alarmeModel.setAlarme(id, view.getViewActivity(), true);
    }

    @Override
    public void okOnClick() {
        mp.stop();
        mp.release();
    }
}
