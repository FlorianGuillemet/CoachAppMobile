package com.example.coachemds.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coachemds.R;
import com.example.coachemds.model.Profil;
import com.example.coachemds.outils.MesOutils;

import java.util.ArrayList;

/**
 * Sert à gerer la liste
 */
public class HistoListAdapter extends BaseAdapter
{

    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;


    /**
     * constructeur
     * @param lesProfils
     */
    public HistoListAdapter(Context contexte, ArrayList<Profil> lesProfils)
    {
        this.lesProfils = lesProfils;
        this.inflater = LayoutInflater.from(contexte);
    }

    /**
     * retourne le nombre de lignes de la liste
     * @return
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * retourne l'item de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
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
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        // declaration d'un Holder
        ViewHolder holder;

        // si la ligne n'existe pas encore
        if(view == null) {
            // instanciation de la classe privée
            holder = new ViewHolder();

            // la ligne est construite avec un formatage (inflater) relié à layout_liste_histo
            view = inflater.inflate(R.layout.layout_liste_histo, null);

            // relier les proprietes du Holder avec les objets graphiques du layout_Liste_histo
            holder.txtListDate = (TextView)view.findViewById(R.id.txtListDate);
            holder.btnListSuppr = (ImageButton)view.findViewById(R.id.btnListSuppr);
            holder.txtListIMG = (TextView)view.findViewById(R.id.txtListIMG);

            // affecter le holder à la vue
            view.setTag(holder);

        } else {
            // recuperation du holder dans la ligne existante
            holder = (ViewHolder) view.getTag();
        }
        // valorisation du contenu du holder (donc de la ligne)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(i).getDateMesure()));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(i).getImg()));
        holder.btnListSuppr.setTag(i);

        return view;
    }

    private class ViewHolder
    {
        ImageButton btnListSuppr;
        TextView txtListDate;
        TextView txtListIMG;
    }

}
