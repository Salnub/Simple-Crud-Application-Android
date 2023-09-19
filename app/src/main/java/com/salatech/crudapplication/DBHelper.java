package com.salatech.crudapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(@Nullable Context context) {
        super(context, "MyUserDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserRecord (SN INT primary key ,NAME Text , CONTACT Text, DOB Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop  Table if exists UserRecord");

    }

    public Boolean insertUserData(String name, String contact, String Dob) {
        //to get  databse connection
        SQLiteDatabase DB = this.getWritableDatabase();
        //towrite contentin database
        ContentValues contentValue = new ContentValues();
        contentValue.put("SN", 0);
        contentValue.put("NAME", name);
        contentValue.put("CONTACT", contact);
        contentValue.put("DOB", Dob);
//execute insert query
        Long result = DB.insert("UserRecord", null, contentValue);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateUserData(String name, String contact, String DOB) {
        //to get  databse connection
        SQLiteDatabase DB = this.getWritableDatabase();
        //towrite contentin database
        ContentValues contentValue = new ContentValues();
        //contentValue.put("NAME",name);
        contentValue.put("CONTACT", contact);
        contentValue.put("DOB", DOB);

        //current user record find
        Cursor currentRecord = DB.rawQuery("select * from UserRecord where NAME=?", new String[]{name});
        if (currentRecord.getCount() > 0) {

            int result = DB.update("UserRecord", contentValue, "NAME=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }//end of update  method
    public Boolean deleteUserData(String name) {
        //to get  databse connection
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor findRecord =  DB.rawQuery("select * from UserRecord where NAME=?",new String[]{name});
        if (findRecord.getCount()>0){
            int result = DB.delete("UserRecord","NAME=?",new String[]{name});
            if (result==-1){
                return false;
            }
            else {
                return true;
            }
        }else {
            return false;
        }

    }//end of delete  method

    public Cursor viewUserData() {
        //to get  databse connection
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor findallRecord =  DB.rawQuery("select * from UserRecord ",null);
            return findallRecord;

    }//end of delete  method
}
