package com.example.wagnersilvestre.alarmapp.model.object.alarme;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public class Alarme {
    private int id;
    private int horas;
    private int minutos;
    private boolean ligado;

    public Alarme(int id, int horas, int minutos, boolean ligado){
        this.setId(id);
        this.horas = horas;
        this.minutos = minutos;
        this.ligado = ligado;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public boolean isLigado() {
        return ligado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
