package com.example.coachemds.controleur;

import android.content.Context;

import com.example.coachemds.model.Profil;
import com.example.coachemds.outils.Serializer;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";

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
        if (Controle.instance == null)
        {
            Controle.instance = new Controle();

            // on recupere l'historique
            //recupSerialize(contexte);
        }
        return Controle.instance;

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

        // serialisation du profil cree
        //Serializer.serialize(nomFic, profil, contexte);

    }

    /**
     * Recuperation img de profil
     * @return img
     */
    public float getImg()
    {
        return this.profil.getImg();
    }

    /**
     * Recuperation du message
     * @return message
     */
    public String getMessage()
    {
        return this.profil.getMessage();
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
