package com.example.coachemds.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coachemds.model.AccesDistant;
import com.example.coachemds.model.AccessLocal;
import com.example.coachemds.model.Profil;
import com.example.coachemds.outils.Serializer;
import com.example.coachemds.vue.CalculActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    //private static AccessLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context contexte;
    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

    /**
     * Constructeur en privé
     */
    private Controle()
    {
        super();
    }

    /**
     * Creation de l'instance
     * @return instance
     */
    public static final Controle getInstance( Context contexte )
    {
        if(contexte != null)
        {
            Controle.contexte = contexte;
        }

        if (Controle.instance == null)
        {
            Controle.instance = new Controle();

            // on recupere l'historique
            //recupSerialize(contexte);
            //accesLocal = new AccessLocal(contexte);
            accesDistant = new AccesDistant();
            //profil = accesLocal.recupDernier();
            //accesDistant.envoi("dernier", new JSONArray());
            accesDistant.envoi("tous", new JSONArray());

        }
        return Controle.instance;

    }

    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }

    /**
     * Creation du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte)
    {
        this.profil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(profil);

        Log.d("date", "**********"+new Date());

        // serialisation du profil cree
        //Serializer.serialize(nomFic, profil, contexte);
        //accesLocal.ajout(profil);

        accesDistant.envoi("enreg", profil.convertToJSONArray());

    }


    public void setProfil(Profil profil)
    {
        Controle.profil = profil;
        ((CalculActivity) contexte).recupProfil();
    }


    /**
     * Recuperation img de profil
     * @return img
     */
    public float getImg()
    {
        //return this.profil.getImg();
        return lesProfils.get(lesProfils.size()-1).getImg();
    }

    /**
     * Recuperation du message
     * @return message
     */
    public String getMessage()
    {
        //return this.profil.getMessage();
        return lesProfils.get(lesProfils.size()-1).getMessage();
    }

    /**
     * Recuperation de l'objet serialisé
     * @param contexte
     */
    public static void recupSerialize(Context contexte)
    {
        profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }

    /**
     * recuperation d'une propriete de l'objet deserialisé ( pour l'historique)
     * @return Integer
     */
    public Integer getPoids()
    {
        if (profil == null)
        {
            return null;
        }
        else
        {
            return profil.getPoids();
        }
    }

    /**
     * recuperation d'une propriete de l'objet deserialisé ( pour l'historique)
     * @return Integer
     */
    public Integer getTaille()
    {
        if (profil == null)
        {
            return null;
        }
        else
        {
            return profil.getTaille();
        }
    }

    /**
     * recuperation d'une propriete de l'objet deserialisé ( pour l'historique)
     * @return Integer
     */
    public Integer getAge()
    {
        if (profil == null)
        {
            return null;
        }
        else
        {
            return profil.getAge();
        }
    }

    /**
     * recuperation d'une propriete de l'objet deserialisé ( pour l'historique)
     * @return Integer
     */
    public Integer getSexe()
    {
        if (profil == null)
        {
            return null;
        }
        else
        {
            return profil.getSexe();
        }
    }

}
