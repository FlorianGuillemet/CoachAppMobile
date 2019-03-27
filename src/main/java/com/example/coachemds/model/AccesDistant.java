package com.example.coachemds.model;

import android.util.Log;

import com.example.coachemds.controleur.Controle;
import com.example.coachemds.outils.AccesHTTP;
import com.example.coachemds.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant implements AsyncResponse
{
    // constante
    private static final String SERVERADDR = "http://10.121.61.229/coach/serveurcoach.php";
    private Controle controle;

    /**
     * constructeur
     */
    public AccesDistant()
    {
        controle = Controle.getInstance(null);
    }

    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "***************"+output+"************");

        // decoupage du message reçu avec :
        String[] message = output.split("%");

        // dans message[0] : "enreg", "dernier", "erreur !"
        // dans message[1] : reste du message

        // si il y a 2 cases
        if(message.length > 1)
        {
            if(message[0].equals("enreg"))
            {
                Log.d("enreg","********"+message[1]);
            } else if (message[0].equals("dernier"))
            {
                Log.d("dernier","********"+message[1]);

                try {
                    JSONObject info = new JSONObject(message[1]);
                    // recuperation des données JSON dans des variables
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    String datemesure = info.getString("datemesure");

                    // set d'un objet avec les propriétés du JSON
                    Profil profil = new Profil(new Date(), poids, taille, age, sexe);

                    // appel de la methode setProfil du controle
                    controle.setProfil(profil);

                } catch (JSONException e) {
                    Log.d("Erreur", "Conversion JSON impossible"+ e.toString());
                }
            } else if (message[0].equals("Erreur !"))
            {
                Log.d("Erreur !","********"+message[1]);
            }
        }

    }

    public void envoi(String operation, JSONArray lesDonneesJSON)
    {
        AccesHTTP accesDonnees = new AccesHTTP();

        // lien de delegation
        accesDonnees.delegate = this;

        // ajout de parametres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        // appel au serveur
        accesDonnees.execute(SERVERADDR);
    }

}
