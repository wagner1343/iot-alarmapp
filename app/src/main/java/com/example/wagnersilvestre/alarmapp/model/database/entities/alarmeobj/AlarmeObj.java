package com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Wagner Silvestre on 2/27/2018.
 */

@Entity(tableName = "alarmes")
public class AlarmeObj {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "horas")
    private int horas;

    @ColumnInfo(name = "minutos")
    private int minutos;

    @ColumnInfo(name = "ligado")
    private boolean ligado;

    public AlarmeObj(int id, int horas, int minutos, boolean ligado){
        this.id = id;
        this.horas = horas;
        this.minutos = minutos;
        this.ligado = ligado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}