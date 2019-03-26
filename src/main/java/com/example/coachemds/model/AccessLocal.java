package com.example.coachemds.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coachemds.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccessLocal
{
    // proprietes
    private String nomBase = "bdCoach.sqLite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructeur
     * @param contexte
     */
    public AccessLocal(Context contexte)
    {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    /**
     * ajout d'un profil dans la base de données
     * @param profil
     */
    public void ajout(Profil profil)
    {
        bd = accesBD.getWritableDatabase();

        // requete sql d'insertion dans la base
        String req = "insert into profil (datemesure, poids, taille, age, sexe) values "
                +"(\""+profil.getDateMesure()
                +"\","+profil.getPoids()
                +","+profil.getTaille()
                +","+profil.getAge()
                +","+profil.getSexe()
                +");";

        bd.execSQL(req);

    }

    /**
     * Recuperation du dernier profil de la base de données
     * @return
     */
    public Profil recupDernier()
    {
        bd = accesBD.getReadableDatabase();
        Profil profil = null;

        String req = "select * from profil";

        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        //si je ne suis pas après le dernier, alors je vais le récupérer
        if (!curseur.isAfterLast())
        {
            Date date = new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);

            profil = new Profil(date, poids, taille, age, sexe);
        }

        curseur.close();

        return profil;
    }

}
