package frgp.utn.edu.ar.OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaUsuarios = "create table usuarios (ID integer primary key autoincrement, USERNAME text unique, PASSWORD text, CORREO text unique, ESTADO integer);";
        String tablaParqueos = "create table parqueos (ID integer primary key autoincrement, PATENTE text, TIEMPO Date);";

        db.execSQL(tablaUsuarios);
        db.execSQL(tablaParqueos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB (){
        this.getWritableDatabase();
    }
    public void closeDB (){
        this.close();
    }
    public void insertDB (String nom, ContentValues val){
        this.getWritableDatabase().insert(nom,null,val);
    }

    public void readDB (){
        this.getReadableDatabase();
    }
}
