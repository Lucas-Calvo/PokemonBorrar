package com.grupo2.pokemon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PokemonBD extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pokemon.db";
    public PokemonBD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Pokemon ("+
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "NOMBRE TEXT NOT NULL,"+
                "DESCRIPTION TEXT NOT NULL,"+
                "IMAGEN TEXT NOT NULL);");

        Pokemon p1 = new Pokemon("bulbasaur", "soy bulbasaur", "");
        Pokemon p2 = new Pokemon("ivvysaur", "soy ivysaur", "");
        Pokemon p3 = new Pokemon("venusaur", "soy venusaur", "");
        Pokemon p4 = new Pokemon("charmander", "soy charmander", "");
        Pokemon p5 = new Pokemon("charmeleon", "soy charmeleon", "");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //No se usa
    }

    public void insertarPokemon(Pokemon pokemon){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("NOMBRE", pokemon.getName());
        values.put("DESCRIPCION", pokemon.getDescripcion());
        values.put("IMAGEN", pokemon.getImage());

        db.insert("POKEMON", null, values);
    }
    public List<Pokemon> getAllPokemon(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                PokemonBD.DATABASE_NAME,
                null,
                "id=?",
                null,
                null,
                null,
                null
        );
        List<Pokemon> pokelist = new ArrayList<Pokemon>();

        while(c.getCount()>0 && c.moveToNext()){
            Pokemon poke=new Pokemon("","","");
            poke.setName(c.getString(c.getColumnIndex("NOMBRE")));
            poke.setDescripcion(c.getString(c.getColumnIndex("DESCRIPCION")));
            poke.setImage(c.getString(c.getColumnIndex("IMAGEN")));
            pokelist.add(poke);
        }
        return pokelist;

    }

    public Pokemon getPokemon(){

        Pokemon pokemon = new Pokemon("","","");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                PokemonBD.DATABASE_NAME,
                null,
                "id=?",
                null,
                null,
                null,
                null
        );
        while(c.getCount()>0 && c.moveToNext()){
            pokemon.setName(c.getString(c.getColumnIndex("NOMBRE")));
            pokemon.setDescripcion(c.getString(c.getColumnIndex("DESCRIPCION")));
            pokemon.setImage(c.getString(c.getColumnIndex("IMAGEN")));
        }
        return pokemon;

    }
}
