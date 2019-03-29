package com.example.coachemds.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.coachemds.R;
import com.example.coachemds.controleur.Controle;
import com.example.coachemds.model.Profil;

import java.util.ArrayList;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle = Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }

    /**
     * Retour au menu
     */
    private void ecouteRetourMenu()
    {
        ((ImageButton)findViewById(R.id.btnRetourDeHisto)).setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });
    }

    /**
     * creer la liste adapter
     */
    private void creerListe()
    {
        ArrayList<Profil> lesProfils = controle.getLesProfils();

        if(lesProfils != null) {
            ListView lstHisto = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(this, lesProfils);
            lstHisto.setAdapter(adapter);
        }
    }

}
