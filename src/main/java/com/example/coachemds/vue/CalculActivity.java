package com.example.coachemds.vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachemds.R;
import com.example.coachemds.controleur.Controle;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    // proprietes
    private EditText    txtPoids;
    private EditText    txtTaille;
    private EditText    txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView    lblIMG;
    private ImageView   imgSmiley;
    private Controle    controle;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init()
    {
        this.txtPoids   = (EditText) findViewById(R.id.txtPoids);
        this.txtTaille  = (EditText) findViewById(R.id.txtTaille);
        this.txtAge     = (EditText) findViewById(R.id.txtAge);
        this.rdHomme    = (RadioButton) findViewById(R.id.rdHomme);
        this.rdFemme    = (RadioButton) findViewById(R.id.rdFemme);
        this.lblIMG     = (TextView) findViewById(R.id.lblIMG);
        this.imgSmiley  = (ImageView) findViewById(R.id.imgSmiley);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
        recupProfil();


    }

    /**
     * Ecoute evenement sur bouton calcul
     */
    private void ecouteCalcul()
    {
        findViewById(R.id.btnCalc).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(CalculActivity.this, "test", Toast.LENGTH_SHORT).show();
                //Log.d("message", "clic ok sur le bouton calcul **********");

                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;

                // Recuperation des donnees saisies
                try
                {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch(Exception e)
                {
                }

                if (rdHomme.isChecked())
                {
                    sexe = 1;
                }

                // controle des données saisies
                // si une des valeurs est nulle, message d'erreur
                if(poids == 0 || taille == 0 || age == 0)
                {
                    Toast.makeText(CalculActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                } else {
                    // sinon, methode afficheResult
                    afficheResult(poids, taille, age, sexe);

                }

            }
        });
    }

    /**
     * Affichage de l'IMG, du message et de l'image en recevant les données d'entrée
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe)
    {
        // creation du profil et recuperation des informations
        this.controle.creerProfil(poids, taille, age, sexe, this);
        float img = this.controle.getImg();
        String message = this.controle.getMessage();

        // affichage des resultats
        if (message == "normal")
        {
            imgSmiley.setImageResource(R.drawable.pvert);
            lblIMG.setTextColor(Color.GREEN);
        }
        else
        {
            imgSmiley.setImageResource(R.drawable.prouge);
            lblIMG.setTextColor(Color.RED);
        }

        lblIMG.setText( "Score IMG : "+String.format("%.01f", img)+ ", votre IMG est : "+message);

    }

    /**
     * Recuperation du profil (s'il a été sérialisé) afin de les valoriser dans les champs graphiques
     */
    public void recupProfil()
    {
        // condition de verification qu'un objet a bien ete serialisé
        if(controle.getPoids() != null)
        {
            this.txtPoids.setText(controle.getPoids().toString());
            this.txtTaille.setText(controle.getTaille().toString());
            this.txtAge.setText(controle.getAge().toString());
            // radio buton femme checké à true par defaut
            this.rdFemme.setChecked(true);
            // check de radio buton homme si sexe = 1
            if(controle.getSexe() == 1)
            {
                this.rdHomme.setChecked(true);
            }
            // on veut la simulation du bouton calculer
            //findViewById(R.id.btnCalc).performClick();

        }
    }

    /**
     * Retour au menu
     */
    private void ecouteRetourMenu()
    {
        ((ImageButton)findViewById(R.id.btnRetourDeCalcul)).setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });
    }

}
