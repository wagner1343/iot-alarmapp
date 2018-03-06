package com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public interface MenuPrincipalPresenter {



    void onResume();
    void onActivityResult(int requestCode, int resultCode, Intent data);

    void adicionarAlarmeOnClick(Activity view);
    void deletarOnClick(Activity view);
    void listViewOnCheckChanged(int id, boolean isLigado);


}
