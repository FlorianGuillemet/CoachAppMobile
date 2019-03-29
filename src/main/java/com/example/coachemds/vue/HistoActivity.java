package com.example.coachemds.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coachemds.R;

public class HistoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        ecouteRetourMenu();
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
}
