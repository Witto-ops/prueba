package com.CDH.myapplication.ui.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class DbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 2;
    private static String DATABASE_NAME = "cdh_db";
    private static String TABLE_NAME = "ficha";
    public static String CODIGO = "codigo";
    public static String ACARGO = "acargo";
    public static String PROYECTO = "proyecto";
    public static String ASIGNADA = "asignada";
    public static String DETALLE = "detalle";
    public static String FECHA = "fecha";
    public static String ESTADO = "estado";
    public static String DESAYUNO = "desayuno";
    public static String ALMUERZO = "almuerzo";
    public static String CENA = "cena";
    public static String AGUA = "agua";
    public static String ALOJAMIENTO = "alojamiento";
    public static String COMBUSTIBLE = "combustible";
    public static String PEAJE = "peaje";
    public static String ESTACIONAMIENTO = "estacionamiento";


    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + CODIGO + " TEXT," + ACARGO+ " TEXT," + PROYECTO+ " TEXT," + ASIGNADA+ " TEXT," + FECHA+ " TEXT," + ESTADO+ " TEXT,"
            + DESAYUNO+ " int,"+ ALMUERZO+ " int,"+ CENA+ " int,"+ AGUA+ " int,"+ ALOJAMIENTO+ " int,"+ COMBUSTIBLE+ " int,"+ PEAJE+ " int,"+ ESTACIONAMIENTO+ " int,"
            + DETALLE+" TEXT)";


    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert data into database
    public void insertIntoTheDatabase(String codigo, String acargo, String proyecto, String asignada, String detalle, String fecha, String estado) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CODIGO, codigo);
        cv.put(ACARGO, acargo);
        cv.put(PROYECTO, proyecto);
        cv.put(ASIGNADA, asignada);
        cv.put(DETALLE, detalle);
        cv.put(FECHA, fecha);
        cv.put(ESTADO, estado);

        db.insert(TABLE_NAME,null, cv);
       // Log.d("FavDB Status", idLocal + ", favstatus - "+status+" - . " + cv);
    }

    // read all data
    public Cursor read_all_data(String codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + CODIGO+"='"+codigo+"'";
        return db.rawQuery(sql,null,null);
    }

    //remove line from database
    public void remove_fav(String codigo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ ESTADO+" ='false' WHERE "+CODIGO+"='"+codigo+"'";
        db.execSQL(sql);
       // Log.d("remove", String.valueOf(codigo));

    }

    public void add_parts(String codigo,int desayuno, int almuerzo, int cena, int agua, int alojamiento, int combustible, int peaje, int estacionamiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  " +DESAYUNO+"='"+desayuno+"',"+ ALMUERZO+"='"+almuerzo+"',"+ CENA+"='"+cena+"',"+ AGUA+"='"+agua+"',"+ ALOJAMIENTO+"='"+alojamiento+"',"+ COMBUSTIBLE+"='"+combustible+"',"+ PEAJE+"='"+peaje+"',"+ ESTACIONAMIENTO+"='"+estacionamiento+"' WHERE "+CODIGO+"='"+codigo+"'";
        db.execSQL(sql);
      //  Log.d("remove", String.valueOf(codigo));

    }


    public void edit_parts_one(String codigo, String acargo, String proyecto, String asignada, String detalle, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  " +ACARGO+"='"+acargo+"',"+ PROYECTO+"='"+proyecto+"',"+ ASIGNADA+"='"+asignada+"',"+ DETALLE+"='"+detalle+"',"+ FECHA+"='"+fecha+"' WHERE "+CODIGO+"='"+codigo+"'";
        db.execSQL(sql);
       // Log.d("remove", String.valueOf(codigo));

    }

    //exist?
    public boolean if_exist(String codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from  " + TABLE_NAME + " where " + CODIGO+"='"+codigo+"' and "+ESTADO+"= 'true'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+ESTADO+" ='true'";
        return db.rawQuery(sql,null,null);
    }
}
