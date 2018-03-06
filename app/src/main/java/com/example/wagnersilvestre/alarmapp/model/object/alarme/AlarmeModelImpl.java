package com.example.wagnersilvestre.alarmapp.model.object.alarme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.wagnersilvestre.alarmapp.model.database.AlarmeAppDb;
import com.example.wagnersilvestre.alarmapp.model.database.entities.alarmeobj.AlarmeObj;
import com.example.wagnersilvestre.alarmapp.userinterface.despertar.DespertarViewImpl;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public class AlarmeModelImpl implements AlarmeModel {
    private AlarmeAppDb alarmeAppDb;
    private ArrayList<Alarme> alarmesList;

    public AlarmeModelImpl(Context context){
        this.alarmeAppDb = AlarmeAppDb.getAlarmDb(context);
        this.alarmesList = new ArrayList<>();
    }

    /**
     * @param horas
     * @param minutos
     * @return Id do Alarme criado
     */
    @Override
    public long criarAlarme(int horas, int minutos) {
        return alarmeAppDb.alarmeObjDao().insert(new AlarmeObj(0, horas, minutos, true));
    }

    /**Deleta um alarme armazenado
     * @param alarme
     */
    @Override
    public void deletar(Alarme alarme) {
        AlarmeObj alarmeObj = new AlarmeObj(alarme.getId(), alarme.getHoras(), alarme.getMinutos(), alarme.isLigado());
        alarmeAppDb.alarmeObjDao().delete(alarmeObj);
    }

    /**
     * Deleta todos alarmes armazenados
     */
    @Override
    public void deletarTodos() {
        atualizarAlarmesList();

        for(Alarme alarme:alarmesList){
            deletar(alarme);
        }

        atualizarAlarmesList();
    }

    /**
     * @return Retorna todos alarmes armazenados
     */
    @Override
    public ArrayList<AlarmeObj> buscarTodos() {
        return (ArrayList<AlarmeObj>) alarmeAppDb.alarmeObjDao().getAll();
    }

    /**
     * @return Retorna o vetor de alarmes salvo na memória
     */
    @Override
    public ArrayList<Alarme> getAlarmesList(){
        return this.alarmesList;
    }

    /**
     * Atualiza a lista de alarmes armazenada na memória
     */
    @Override
    public void atualizarAlarmesList(){
        ArrayList<AlarmeObj> alarmesObjList = buscarTodos();
        alarmesList.clear();

        for(AlarmeObj alarmeObj:alarmesObjList) {
            alarmesList.add(new Alarme(alarmeObj.getId(), alarmeObj.getHoras(), alarmeObj.getMinutos(), alarmeObj.isLigado()));
        }
    }

    /**
     * Marca ou desmarca um alarme no sistema dependendo do valor da variável "on"
     * @param id Id do alarme, será utilizado para definir o horário do alarme
     * @param act Activity que proverá contexto para o alrme
     * @param on Se for verdadeiro o alarme será marcado, caso contrário sera desmarcado
     * @return Retorna se o alarme foi marcado com sucesso ou não
     */
    @Override
    public boolean setAlarme(int id, Activity act, boolean on) {
        AlarmManager alarmManager = (AlarmManager) act.getSystemService(Activity.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        AlarmeObj alarme = alarmeAppDb.alarmeObjDao().getAlarmeById(id);
        if(alarme == null) return false;

        calendar.setTimeInMillis(System.currentTimeMillis());
        long now = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, alarme.getHoras());
        calendar.set(Calendar.MINUTE, alarme.getMinutos());
        calendar.set(Calendar.SECOND, 0);
        long target = calendar.getTimeInMillis();

        long timeDiff = (target - now);
        long interval;
        System.out.println("Difference in time: target - now = " + timeDiff);

        if(timeDiff > 5 * 1000){
            interval = calendar.getTimeInMillis();
        }
        else{
            interval = calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY - timeDiff;
        }

        Intent intent = new Intent( act, DespertarViewImpl.class);
        intent.putExtra("alarme_id", alarme.getId());

        int requestId = alarme.getId();
        int alarmeFlag = 0;
        boolean resultado;

        PendingIntent alarmIntent = PendingIntent.getActivity(act, requestId, intent, alarmeFlag);

        if(on) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, interval, alarmIntent);

            if ((PendingIntent.getActivity(act, requestId, intent, PendingIntent.FLAG_NO_CREATE)) != null) {

                System.out.println("Alarme " + alarme.getId() + " ligado para as " + alarme.getHoras() + ":" + alarme.getMinutos());

                resultado = true;
            } else {
                System.out.println("Falha ai ligar o alarme " + alarme.getId());
                resultado = false;
            }
            System.out.println("interval = " + interval);
        }
        else {
            alarmManager.cancel(alarmIntent);

            if ((PendingIntent.getActivity(act, requestId, intent, PendingIntent.FLAG_NO_CREATE)) != null) {

                System.out.println("Alarme " + alarme.getId() + " desligado");
                resultado = false;
            } else {
                System.out.println("Falha ao desligar o alarme " + alarme.getId());
                resultado = true;
            }
        }

        alarmeAppDb.alarmeObjDao().setLigadoById(resultado, alarme.getId());

        return resultado;
    }

    /**
     * Desliga todos alarmes armazenados
     * @param act Contexto a partir do qual os alarmes foram criados
     */
    @Override
    public void desligarTodos(Activity act) {
        atualizarAlarmesList();

        for(Alarme alarme:alarmesList){
            setAlarme(alarme.getId(), act, false);
        }
    }
}
