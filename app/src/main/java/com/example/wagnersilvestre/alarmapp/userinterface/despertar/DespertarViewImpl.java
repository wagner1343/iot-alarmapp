package com.example.wagnersilvestre.alarmapp.userinterface.despertar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wagnersilvestre.alarmapp.R;

public class DespertarViewImpl extends Activity implements DespertarView {
    TextView textViewHorario;
    DespertarPresenter presenter;
    TextView textViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despertar);

        textViewHorario = (TextView) findViewById(R.id.textViewHorario);
        textViewId = (TextView) findViewById(R.id.textViewId);

        presenter = new DespertarPresenterImpl(this);
    }

    public void okOnClick(View view){
        presenter.okOnClick();
        finish();
    }

    @Override
    public void mostrarHorario(String horario) {
        textViewHorario.setText(horario);
    }

    @Override
    public void mostrarId(String id) {
        textViewId.setText(id);
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }
}
