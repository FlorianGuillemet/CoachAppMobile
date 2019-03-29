package com.example.coachemds.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coachemds.R;
import com.example.coachemds.controleur.Controle;

public class MainActivity extends AppCompatActivity
{

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controle = Controle.getInstance(this);

        ecouteMenu((ImageButton) findViewById(R.id.btnMenuIMG), CalculActivity.class);

        ecouteMenu((ImageButton) findViewById(R.id.btnMenuHistorique), HistoActivity.class);

    }

    /**
     * Ouvrir l'activity correspondante
     * @param btn
     * @param classe
     */
    private void ecouteMenu(ImageButton btn, final Class classe)
    {
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, classe);
                //ferme l'activite
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }

}
