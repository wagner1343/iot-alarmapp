package com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal;

import android.app.Activity;
import android.content.Intent;

import com.example.wagnersilvestre.alarmapp.model.object.alarme.AlarmeModel;
import com.example.wagnersilvestre.alarmapp.model.object.alarme.AlarmeModelImpl;
import com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal.adicionaralarme.AdicionarAlarmeActivity;
import com.example.wagnersilvestre.alarmapp.userinterface.widget.adapter.AlarmeListAdapter;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public class MenuPrincipalPresenterImpl implements MenuPrincipalPresenter {
    private MenuPrincipalView view;
    private AlarmeModel alarmeModel;

    public MenuPrincipalPresenterImpl(MenuPrincipalView view){
        this.view = view;
        alarmeModel = new AlarmeModelImpl(view.getViewActivity().getBaseContext());
        view.setListViewAdapter(new AlarmeListAdapter(alarmeModel.getAlarmesList(), view.getViewActivity(), this));
    }

    @Override
    public void listViewOnCheckChanged(int id, boolean isLigado){
        alarmeModel.setAlarme(id, view.getViewActivity(), isLigado);
        alarmeModel.atualizarAlarmesList();
        view.listViewNotifyDatasetChanged();
    }

    @Override
    public void onResume() {
        alarmeModel.atualizarAlarmesList();
        view.listViewNotifyDatasetChanged();
    }

    @Override
    public void adicionarAlarmeOnClick(Activity view) {
        Intent intent = new Intent(view, AdicionarAlarmeActivity.class);
        view.startActivityForResult(intent, RequestCode.CRIAR_ALARME);
    }

    @Override
    public void deletarOnClick(Activity view){
        alarmeModel.desligarTodos(this.view.getViewActivity());
        alarmeModel.deletarTodos();
        this.view.listViewNotifyDatasetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCode.CRIAR_ALARME:
                if (resultCode == Activity.RESULT_OK) {
                    int horas = data.getIntExtra("horas", 0);
                    int minutos = data.getIntExtra("minutos", 0);
                    int id = (int) alarmeModel.criarAlarme(horas, minutos);
                    listViewOnCheckChanged(id, true);
                }
                break;
        }
    }
}


