package com.grupo2.pokemon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
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

        Pokemon p1 = new Pokemon(0,"bulbasaur", "soy bulbasaur", "");
        insertarPokemon(p1,sqLiteDatabase);
        Pokemon p2 = new Pokemon(1,"ivvysaur", "soy ivysaur", "");
        insertarPokemon(p2,sqLiteDatabase);
        Pokemon p3 = new Pokemon(2,"venusaur", "soy venusaur", "");
        insertarPokemon(p3,sqLiteDatabase);
        Pokemon p4 = new Pokemon(3,"charmander", "soy charmander", "");
        insertarPokemon(p4,sqLiteDatabase);
        Pokemon p5 = new Pokemon(4,"charmeleon", "soy charmeleon", "");
        insertarPokemon(p5,sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //No se usa
    }

    public void insertarPokemon(Pokemon pokemon, SQLiteDatabase db){

        if(db==null){
            db = getWritableDatabase();
        }

        ContentValues values = new ContentValues();

        values.put("_ID", pokemon.getId());
        values.put("NOMBRE", pokemon.getName());
        values.put("DESCRIPTION", pokemon.getDescripcion());
        values.put("IMAGEN", pokemon.getImage());

        db.insert("POKEMON", null, values);
    }
    @SuppressLint("Range")
    public List<Pokemon> getAllPokemon(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                "Pokemon",
                null,
                null,
                null,
                null,
                null,
                null
        );
        List<Pokemon> pokelist = new ArrayList<>();

        while(c.moveToNext()){
            Pokemon poke=new Pokemon(0,"","","");
            poke.setId(c.getInt((c.getColumnIndex("_ID"))));
            poke.setName(c.getString(c.getColumnIndex("NOMBRE")));
            poke.setDescripcion(c.getString(c.getColumnIndex("DESCRIPTION")));
            poke.setImage(c.getString(c.getColumnIndex("IMAGEN")));
            pokelist.add(poke);
        }
        return pokelist;

    }

    @SuppressLint("Range")
    public Pokemon getPokemon(String id){

        Pokemon pokemon = new Pokemon(0,"","","");
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                "Pokemon",
                null,
                "id=?",
                new String[]{id},
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

    public void añadirPokemon(String nombre){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db==null){
            db = getWritableDatabase();
        }
        ContentValues values = new ContentValues();

        values.put("NOMBRE", nombre);
        values.put("DESCRIPTION", "Soy "+nombre);
        values.put("IMAGEN", " ");

        db.insert("POKEMON", null, values);
    }
}
