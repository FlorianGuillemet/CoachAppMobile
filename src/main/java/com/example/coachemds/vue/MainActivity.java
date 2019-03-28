package com.example.coachemds.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coachemds.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ecouteMenu((ImageButton) findViewById(R.id.btnMenuIMG), CalculActivity.class);

        //ecouteMenu((ImageButton) findViewById(R.id.btnMenuHistorique), HistoActivity.class);

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

                startActivity(intent);

            }
        });
    }

}
