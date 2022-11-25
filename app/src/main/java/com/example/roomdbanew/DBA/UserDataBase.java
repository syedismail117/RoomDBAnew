package com.example.roomdbanew.DBA;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database ( entities = {Users.class},version = 2)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getDao();

    public static UserDataBase INSTANCE;

    public static UserDataBase getInstance(Context context){


        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder (context,UserDataBase.class,"UserDB").allowMainThreadQueries ()
                    .fallbackToDestructiveMigration ()
                    .build ();
        }
        return INSTANCE;
    }

}
