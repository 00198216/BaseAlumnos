package com.example.charl.basealumnos.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.charl.basealumnos.Data.Alumno;

import static java.lang.Integer.parseInt;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bd_usuarios";
    public static final String TABLA_USUARIO = "Persona";
    public static final String CAMPO_ID = "Carnet";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_NOTA = "Nota";
    public static final String SACAR_PROMEDIO = "SELECT AVG("+CAMPO_NOTA +") FROM "+TABLA_USUARIO;
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + "(" + CAMPO_ID + " TEXT," + CAMPO_NOMBRE + " TEXT," + CAMPO_NOTA + " TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx) {
        if (myDB == null) {
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAMPO_NOMBRE);
        onCreate(db);
    }

    public boolean add(Alumno p) {
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID, p.getCarnet());
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, "-");

        db.insert(TABLA_USUARIO, null, values);
        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Alumno findUser(String carnet) {
        Alumno p;
        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOMBRE};
        String[] camposn = {CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();

            Cursor cursorn = db.query(TABLA_USUARIO, camposn, CAMPO_ID + "=?", parametros, null, null, null);
            cursorn.moveToFirst();

            p = new Alumno(carnet, cursor.getString(0), cursorn.getString(0));
            cursor.close();
            cursorn.close();

        } catch (Exception e) {
            p = null;
        }
        return p;
    }

    public boolean editUser(Alumno p) {
        String[] parametros = {p.getCarnet()};
        String[] campos = {CAMPO_NOMBRE};
        String[] camposn = {CAMPO_NOTA};
        ContentValues values = new ContentValues();
        ContentValues values2 = new ContentValues();
        values.put(CAMPO_NOMBRE, p.getNombre());
        values2.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIO, values, CAMPO_ID + "=?", parametros);
        db.update(TABLA_USUARIO, values2, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Usuario Actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean deleteUser(String carnet) {
        String[] parametros = {carnet};
        db.delete(TABLA_USUARIO, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Usuario Eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public float promedio() {

        float Avg;
        Cursor prom = db.rawQuery(SACAR_PROMEDIO, null);
        prom.moveToFirst();


        Avg =(prom.getFloat(0));

        prom.close();


        return Avg;
    }
}

