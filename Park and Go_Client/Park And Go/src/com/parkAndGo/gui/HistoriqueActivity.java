package com.parkAndGo.gui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HistoriqueActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_historique, menu);
        return true;
    }
}
