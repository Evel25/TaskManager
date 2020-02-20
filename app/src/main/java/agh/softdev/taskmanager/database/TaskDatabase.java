package agh.softdev.taskmanager.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import agh.softdev.taskmanager.database.daos.TaskDao;
import agh.softdev.taskmanager.database.entities.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instanse;
    public static synchronized TaskDatabase getInstance(Context context){
        if(instanse == null){
            instanse = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"tasks_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instanse;
    }

    public abstract TaskDao taskDao();

    // this callback used to create some items in the database whene the database
    // is first created
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitDbAsyncTask(instanse).execute();
        }
    };

    // asynktask to add new taks to the database
    private static class InitDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private TaskDao taskDao;

        public InitDbAsyncTask(TaskDatabase database){
            this.taskDao = database.taskDao();
        }
        @Override
        protected Void doInBackground(Void... tasks) {
            this.taskDao.insert(new Task("task1","description 1"));
            this.taskDao.insert(new Task("task2","description 2"));
            this.taskDao.insert(new Task("task3","description 3"));
            return null;
        }
    }

}
