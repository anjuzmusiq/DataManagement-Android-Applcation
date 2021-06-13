package com.example.datamanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.nio.file.FileAlreadyExistsException;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "data.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create Table userdetails(name TEXT primary key,contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if EXISTS userdetails");

    }

    public boolean insertdetail(String name,String contact, String dob) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long result=db.insert("userdetails",null, contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean updatedetail(String name,String contact, String dob) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        Cursor cursor=db.rawQuery("Select * from userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.update("userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public boolean deletedetail(String name) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from userdetails where name=?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = db.delete("userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public Cursor getdetail() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from userdetails",null);
        return cursor;
    }
}
