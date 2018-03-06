package com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.wagnersilvestre.alarmapp.R;

public class MenuPrincipalViewImpl extends Activity implements MenuPrincipalView {
    ListView listViewAlarmes;
    MenuPrincipalPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        listViewAlarmes = (ListView) findViewById(R.id.listViewAlarmes);
        presenter = new MenuPrincipalPresenterImpl(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void setListViewAdapter(BaseAdapter adapter){
        listViewAlarmes.setAdapter(adapter);
    }

    @Override
    public void listViewNotifyDatasetChanged(){
        ((BaseAdapter) listViewAlarmes.getAdapter()).notifyDataSetChanged();
    }

    public void adicionarAlarmeOnClick(View view) {
        presenter.adicionarAlarmeOnClick(this);
    }

    public void buttonDeletarOnClick(View view) {
        presenter.deletarOnClick(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}
