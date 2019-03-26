package com.example.coachemds.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    // creation profil
    private Profil profilFEleve = new Profil(67, 165, 35, 0);
    // resultat IMG
    private float imgFaux = (float) 32.4;
    private float imgOk = (float) 32.2;
    // message
    private String message = "trop élevé";

    @Test
    public void getImgFaux()
    {
        assertNotEquals(imgFaux, profilFEleve.getImg(), (float)0.1);
    }

    @Test
    public void getImgOk()
    {
        assertEquals(imgOk, profilFEleve.getImg(), (float)0.1);
    }

    @Test
    public void getMessage()
    {
        assertEquals(message, profilFEleve.getMessage());
    }
}