package com.podevs.android.pokemononline.battle;

import java.util.Locale;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.podevs.android.pokemononline.R;
import com.podevs.android.pokemononline.poke.Poke;
import com.podevs.android.pokemononline.pokeinfo.AbilityInfo;
import com.podevs.android.pokemononline.pokeinfo.InfoConfig;
import com.podevs.android.pokemononline.pokeinfo.ItemInfo;
import com.podevs.android.pokemononline.pokeinfo.MoveInfo;
import com.podevs.android.pokemononline.pokeinfo.PokemonInfo;
import com.podevs.android.pokemononline.pokeinfo.TypeInfo;

public class ListedPokemon {
	TextView name, item, ability, hp;
	ImageView icon;
	TextView [] moves = new TextView[4];
	RelativeLayout whole;
		
	public ListedPokemon(RelativeLayout whole2) {
		init(whole2);
	}

	void init(RelativeLayout lay) {
		whole = lay;
		
		name = (TextView) whole.findViewById(R.id.pokename);
		hp = (TextView) whole.findViewById(R.id.hp);
		item = (TextView) whole.findViewById(R.id.item);
		ability = (TextView) whole.findViewById(R.id.ability);
		
		moves[0] = (TextView) whole.findViewById(R.id.attack1);
		moves[1] = (TextView) whole.findViewById(R.id.attack2);
		moves[2] = (TextView) whole.findViewById(R.id.attack3);
		moves[3] = (TextView) whole.findViewById(R.id.attack4);
        
		icon = (ImageView) whole.findViewById(R.id.pokeViewIcon);
	}
	
	public void update(Poke poke) {
		this.update(poke, true);
	}
	
	public void update(Poke poke, boolean canSwitch) {
		icon.setImageDrawable(PokemonInfo.icon(poke.uID()));
		name.setText(poke.nick());
		hp.setText(poke.currentHP() + "/" + poke.totalHP());
		item.setText(ItemInfo.name(poke.item()));
		ability.setText(AbilityInfo.name(poke.ability()));
		for (int j = 0; j < 4; j++) {
			moves[j].setText(poke.move(j).toString());
			moves[j].setShadowLayer((float)1, 1, 1, InfoConfig.resources.getColor(
					canSwitch ? R.color.poke_text_shadow_enabled : R.color.poke_text_shadow_disabled));
        	String type;
        	if (poke.move(j).num() == 237)
        		type = TypeInfo.name(poke.hiddenPowerType());
        	else
        		type = TypeInfo.name(MoveInfo.type(poke.move(j).num()));
        	type = type.toLowerCase(Locale.UK);
        	moves[j].setBackgroundResource(InfoConfig.resources.getIdentifier(type + "_type_button",
		      		"drawable", InfoConfig.pkgName));
		}
	}
	
	void setEnabled(int num, boolean enabled) {
    	setLayoutEnabled(whole, enabled);
    	setTextViewEnabled(name, enabled);
    	setTextViewEnabled(item, enabled);
    	setTextViewEnabled(ability, enabled);
    	setTextViewEnabled(hp, enabled);
    	//setTextViewEnabled(pokeListIcons[num], enabled);
    	for(int i = 0; i < 4; i++)
    		setTextViewEnabled(moves[i], enabled);
    }
	
	void setLayoutEnabled(ViewGroup v, boolean enabled) {
    	v.setEnabled(enabled);
    	v.getBackground().setAlpha(enabled ? 255 : 128);
    }
    
    void setTextViewEnabled(TextView v, boolean enabled) {
    	v.setEnabled(enabled);
    	v.setTextColor(v.getTextColors().withAlpha(enabled ? 255 : 128).getDefaultColor());
    }
}
