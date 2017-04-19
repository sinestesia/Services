package es.pamp.services.es.pamp.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class BBDD extends SQLiteOpenHelper {



    public BBDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE REGISTROS(ID INTEGER PRIMARY KEY AUTOINCREMENT, MENSAJE TEXT, NOTIFICADO INTEGER);";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE If Exists REGISTROS");
    }
    public void guardarRegistro (SQLiteDatabase db, String mensaje){
        // 0 Notificación no enviada, 1 Notificación enviada
        String sqlInsert = "INSERT INTO REGISTROS (MENSAJE, NOTIFICADO) VALUES ('" + mensaje + "',0);";
        db.execSQL(sqlInsert);

    }
    public String leerMensaje (SQLiteDatabase db){
        String mensaje="";
        int id;

        String sqlSelect = "SELECT * FROM REGISTROS WHERE NOTIFICADO = 0";
        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            mensaje=cursor.getString(1);
            String sqlUpdate = "UPDATE REGISTROS SET NOTIFICADO =1 WHERE ID=" + id;
            db.execSQL(sqlUpdate);
        }

        return mensaje;
    }




}
