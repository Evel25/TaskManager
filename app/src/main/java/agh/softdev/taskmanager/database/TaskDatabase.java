package agh.softdev.taskmanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import agh.softdev.taskmanager.database.daos.TaskDao;
import agh.softdev.taskmanager.database.entities.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instanse;
    public static synchronized TaskDatabase getInstance(Context context){
        if(instanse == null){
            instanse = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"tasks_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanse;
    }

    public abstract TaskDao taskDao();

}
