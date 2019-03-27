package com.example.coachemds.model;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable
{

    // constantes de classe
    private static final Integer    MINFEMME = 15;
    private static final Integer    MAXFEMME = 30;
    private static final Integer    MINHOMME = 10;
    private static final Integer    MAXHOMME = 25;
    private static final String     MSGFAIBLE = "trop faible";
    private static final String     MSGELEVE = "trop élevé";
    private static final String     MSGNORMAL = "normal";

    // proprietes
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float   img;
    private String  message;


    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe)
    {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculImg();
        this.resultImg();
    }


    public Date getDateMesure()
    {
        return dateMesure;
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    // methodes

    private void calculImg()
    {
        float tailleM = (float) this.taille/100;
        this.img = (float) ( 1.2 * this.poids / ( tailleM * tailleM ) + ( 0.23 * this.age ) - ( 10.83 * this.sexe ) - 5.4 );
    }

    private void resultImg()
    {
        Integer min;
        Integer max;

        if(this.sexe == 0) //femme
        {
            min = MINFEMME;
            max = MAXFEMME;
        }
        else {  // homme
            min = MINHOMME;
            max = MAXHOMME;
        }

        // message correspondant
        if (this.img < min )
        {
            this.message = MSGFAIBLE;
        }
        else if (this.img > max )
        {
            this.message = MSGELEVE;
        }
        else {
            this.message = MSGNORMAL;
        }

    }

    /**
     * conversion du profil au format JSON ARRAY
     * @return
     */
    public JSONArray convertToJSONArray()
    {
        List laListe = new ArrayList();
        laListe.add(dateMesure);
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);

        return new JSONArray(laListe);
    }


}
