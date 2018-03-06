package com.example.wagnersilvestre.alarmapp.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj.AlarmeObj;
import com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj.AlarmeObjDao;

/**
 * Created by Wagner Silvestre on 2/27/2018.
 */

@Database(entities = {AlarmeObj.class}, version =  1)
public abstract class AlarmeAppDb extends RoomDatabase {

    private static AlarmeAppDb alarmeAppDb;

    public abstract AlarmeObjDao alarmeObjDao();

    public static AlarmeAppDb getAlarmDb(Context context){
        if(alarmeAppDb == null){
            alarmeAppDb = Room.databaseBuilder(context.getApplicationContext(), AlarmeAppDb.class, "alarme-database").allowMainThreadQueries().build();
        }

        return alarmeAppDb;
    }

    public void destroyAlarmDb(){
        alarmeAppDb = null;
    }
}
