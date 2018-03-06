package com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal;

import android.widget.BaseAdapter;

import com.example.wagnersilvestre.alarmapp.userinterface.BaseViewInterface;

/**
 * Created by Wagner Silvestre on 2/28/2018.
 */

public interface MenuPrincipalView extends BaseViewInterface {
    void setListViewAdapter(BaseAdapter adapter);
    void listViewNotifyDatasetChanged();
}
