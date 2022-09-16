package com.example.insorma_kelompok.Database;

import static com.example.insorma_kelompok.Activity.Register.accounts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.insorma_kelompok.Account.Acc;

public class DB extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_insorma";
    public static final String TB_NAME = "db_acc";
    public static final String COL_1 = "email";
    public static final String COL_2 = "username";
    public static final String COL_3 = "phone";
    public static final String COL_4 = "pass";

    public DB (Context context){
        super(context, DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_create = " CREATE TABLE " + TB_NAME + "(" + COL_1 + " text primary key,"
                + COL_2 + " text not null,"
                + COL_3 + " text not null,"
                + COL_4 + " text not null" + ")";
        sqLiteDatabase.execSQL(sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert (String in_email, String in_username, String in_phone, String in_pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB.COL_1, in_email );
        values.put(DB.COL_2, in_username );
        values.put(DB.COL_3, in_phone);
        values.put(DB.COL_4, in_pass );
        db.insert(TB_NAME, null, values);
        db.close();
    }

    public void update (String in_username, String in_email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, in_username );
//        Cursor cursor = db.rawQuery(" SELECT " + COL_1
//                        + " FROM " + TB_NAME
//                        + " WHERE " + COL_1 + " = " + "'" + in_email + "'"
//                ,null);
        String [] where = new String[]{in_email};
//        if(cursor.getCount()>0){
        db.update(TB_NAME, values, "email = ?", where);
        db.close();
//        }
    }
    public void delete (String in_email, String in_username, String in_phone, String in_pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB.COL_2, in_username );
        values.put(DB.COL_3, in_phone );
        values.put(DB.COL_4, in_pass );
        Cursor cursor = db.rawQuery(" SELECT " + COL_1
                        + " FROM " + TB_NAME
                        + " WHERE " + COL_1 + "= ? " + "'" + in_email + "'"
                ,null);
        if(cursor.getCount()>0){
            db.delete(TB_NAME, "email=?", new String[] {in_email});
            db.close();
        }
    }
    public void getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TB_NAME, null);

        if(cursor.moveToFirst()){
            do{
                String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(COL_1));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(COL_3));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_4));

                Acc acc = new Acc(username,userEmail,password,phone);
                accounts.add(acc);

                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }
        cursor.close();
        db.close();
    }

    public Boolean check(String in_email,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT " + COL_1
                        + " FROM " + TB_NAME
                        + " WHERE " + COL_1 + "=" + "'" + in_email + "'"
                        + " AND " + COL_4 + "=" + "'" + pass + "'"
                ,null);
        if (cursor.moveToFirst()) {
            return Boolean.TRUE;
        }
        cursor.close();
        db.close();

        return Boolean.FALSE;
    }
}