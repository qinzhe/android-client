package com.podevs.android.pokemononline.teambuilder;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.podevs.android.pokemononline.R;
import com.podevs.android.pokemononline.poke.Gen;
import com.podevs.android.pokemononline.poke.UniqueID;
import com.podevs.android.pokemononline.pokeinfo.InfoConfig;
import com.podevs.android.pokemononline.pokeinfo.PokemonInfo;
import com.podevs.android.pokemononline.pokeinfo.TypeInfo;
import com.podevs.android.pokemononline.pokeinfo.TypeInfo.Type;

public class PokeListAdapter implements Adapter, ListAdapter {
	private Gen gen = new Gen();
	
	public int getCount() {
		return PokemonInfo.numberOfPokemons();
	}

	public Object getItem(int pos) {
		return pos;
	}

	public long getItemId(int pos) {
		return pos;
	}

	public int getItemViewType(int arg0) {
		return 0;
	}

	public View getView(int pos, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			convertView = ViewGroup.inflate(arg2.getContext(), R.layout.pokeinlist_item, null);
		}

		UniqueID poke = new UniqueID(pos,0);
		ImageView image = (ImageView)convertView.findViewById(R.id.image); 
		image.setImageResource(PokemonInfo.iconRes(poke));
		((TextView)convertView.findViewById(R.id.pokename)).setText(PokemonInfo.name(poke));
		((ImageView)convertView.findViewById(R.id.type1)).setImageResource(TypeInfo.typeRes(PokemonInfo.type1(poke, gen.num)));
		
		int type2 = PokemonInfo.type2(poke, gen.num);
		ImageView itype2 = ((ImageView)convertView.findViewById(R.id.type2));
		
		itype2.setImageResource(TypeInfo.typeRes(type2));
		itype2.setVisibility(type2 == Type.Curse.ordinal() ? View.INVISIBLE : View.VISIBLE);
		
		return convertView;
	}

	public int getViewTypeCount() {
		return 1;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isEmpty() {
		return false;
	}

	public void registerDataSetObserver(DataSetObserver arg0) {
	}

	public void unregisterDataSetObserver(DataSetObserver arg0) {
	}

	public boolean areAllItemsEnabled() {
		return true;
	}

	public boolean isEnabled(int arg0) {
		return true;
	}

}
