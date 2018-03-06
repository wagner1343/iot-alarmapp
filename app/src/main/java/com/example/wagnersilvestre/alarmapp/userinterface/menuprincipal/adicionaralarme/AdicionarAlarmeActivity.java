package com.example.wagnersilvestre.alarmapp.userinterface.menuprincipal.adicionaralarme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.example.wagnersilvestre.alarmapp.R;

public class AdicionarAlarmeActivity extends Activity {
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_alarme);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    public void okOnClick(View view){
        Intent resultado = new Intent();
        resultado.putExtra("horas", timePicker.getHour());
        resultado.putExtra("minutos", timePicker.getMinute());

        setResult(Activity.RESULT_OK, resultado);
        finish();
    }

    public void buttonCancelOnClick(View view){
        finish();
    }
}
