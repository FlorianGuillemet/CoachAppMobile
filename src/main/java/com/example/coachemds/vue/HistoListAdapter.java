package com.example.coachemds.vue;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Sert à gerer la liste
 */
public class HistoListAdapter extends BaseAdapter
{

    /**
     * retourne le nombre de lignes de la liste
     * @return
     */
    @Override
    public int getCount() {
        return 0;
    }

    /**
     * retourne l'item de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return null;
    }

    /**
     * retourne un indice par rapport à la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * retourne la ligne (view) formatée avec gestion des évènements
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
