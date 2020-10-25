package com.CDH.myapplication.ui.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelperPersonal extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "TuxCoholDB";
    private static String TABLE_NAME = "Favoritos";
    public static String NOMBRE = "nombre";
    public static String RUT = "rut";
    public static String SAM = "sam";
    public static String SPM = "spm";
    public static String EAM = "eam";
    public static String EPM = "epm";
    public static String ESTADO = "estado";
    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + NOMBRE + " TEXT," + RUT+ " TEXT," + SAM+ " TEXT,"+ SPM+ " TEXT,"+ EAM+ " TEXT,"+ EPM+ " TEXT,"
            + ESTADO+" TEXT)";


    public DbHelperPersonal(Context context) {
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*/ create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(KEY_ID_LOCAL, x);
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME,null, cv);
        }
    }*/

    // insert data into database
    public void insertIntoTheDatabase(String nombre, String rut, String sam, String spm, String eam, String epm,String estado) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOMBRE, nombre);
        cv.put(RUT, rut);
        cv.put(SAM, sam);
        cv.put(SPM, spm);
        cv.put(EAM, eam);
        cv.put(EPM, epm);
        cv.put(ESTADO, estado);
        db.insert(TABLE_NAME,null, cv);
       // Log.d("FavDB Status", idLocal + ", favstatus - "+status+" - . " + cv);
    }

    // read all data
    public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + RUT+"='"+id+"'";
        return db.rawQuery(sql,null,null);
    }

    // remove line from database
    public void remove_fav(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ ESTADO+" ='false' WHERE "+RUT+"='"+id+"'";
        db.execSQL(sql);
        Log.d("remove", String.valueOf(id));

    }
    public void update(String nombre, String rut, String sam, String spm, String eam, String epm) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  " +NOMBRE+"='"+nombre+"',"+ SAM+"='"+sam+"',"+ SPM+"='"+spm+"',"+ EAM+"='"+eam+"',"+ EPM+"='"+epm+"' WHERE "+RUT+"='"+rut+"'";
        db.execSQL(sql);
        //  Log.d("remove", String.valueOf(codigo));

    }
    //es favorito?
    public boolean is_exist(String rut) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from  " + TABLE_NAME + " where " + RUT+"='"+rut+"' and "+ESTADO+"= 'true'";
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
