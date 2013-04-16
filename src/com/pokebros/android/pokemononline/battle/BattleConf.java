package com.pokebros.android.pokemononline.battle;

import android.util.Log;

import com.pokebros.android.pokemononline.SerializeBytes;
import com.pokebros.android.pokemononline.poke.Gen;
import com.pokebros.android.utilities.Bais;
import com.pokebros.android.utilities.Baos;

public class BattleConf implements SerializeBytes {
	private static final int VERSION = 0;
	private static final String TAG = "Battle Configuration";
	public Gen gen;
	public byte mode = 0;
	public int[] ids = new int[2];
	public int clauses;
	public boolean rated;
	
	public int id(int i) { return ids[i]; }
	public byte mode() { return mode; };
	
	public BattleConf(Bais msg) {
		short len = msg.readShort();
		short version = (short)((short)msg.readByte() & 0xff);
		if (VERSION != version) {
			Log.d(TAG, "Skipped unkown version " + version);
			msg.skip(len - 1);
		}
		
		msg.readFlags();
		rated = msg.readFlags().readBool();

		gen = new Gen(msg);

		mode = msg.readByte();
		clauses = msg.readInt();
		ids[0] = msg.readInt();
		ids[1] = msg.readInt();
	}
	
	public void serializeBytes(Baos b) {
		gen.serializeBytes(b);
		b.write(mode);
		b.putInt(ids[0]);
		b.putInt(ids[1]);
		b.putInt(clauses);
	}
}
