package com.example.wagnersilvestre.alarmapp.model.object.alarme;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

import android.app.Activity;

import com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj.AlarmeObj;

import java.util.ArrayList;

public interface AlarmeModel {
    long criarAlarme(int horas, int minutos);
    void deletar(Alarme alarme);
    void deletarTodos();
    ArrayList<AlarmeObj> buscarTodos();

    void atualizarAlarmesList();
    ArrayList<Alarme> getAlarmesList();

    boolean setAlarme(int id, Activity act, boolean on);
    void desligarTodos(Activity act);
}
