package com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Wagner Silvestre on 2/27/2018.
 */

@Dao
public interface AlarmeObjDao {

    @Query("SELECT * FROM alarmes")
    List<AlarmeObj> getAll();

    @Query("UPDATE alarmes SET ligado= :isLigado WHERE id= :alarmeId")
    int setLigadoById(boolean isLigado, int alarmeId);

    @Query("SELECT * FROM alarmes WHERE id= :alarmeId LIMIT 1")
    AlarmeObj getAlarmeById(int alarmeId);

    @Insert
    long insert(AlarmeObj alarmeObj);

    @Delete
    void delete(AlarmeObj alarmes);
}
