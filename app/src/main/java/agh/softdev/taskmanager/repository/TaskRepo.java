package agh.softdev.taskmanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import java.util.List;
import java.util.zip.DataFormatException;

import agh.softdev.taskmanager.database.TaskDatabase;
import agh.softdev.taskmanager.database.daos.TaskDao;
import agh.softdev.taskmanager.database.entities.Task;

public class TaskRepo {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepo(Application application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAll();
    }

    public void insert(Task task){
        new InsertTaskAsynctask(taskDao).execute(task);
    }

    public void delete(Task task){
        new DeleteTaskAsynctask(taskDao).execute(task);
    }

    public void update(Task task){
        new UpdateTaskAsynctask(taskDao).execute(task);
    }

    public void deleteAll(){
        new DeleteAllTaskAsynctask(taskDao).execute();
    }

    public LiveData<List<Task>> getAllTasks(){
        return  this.allTasks;
    }

    private static class InsertTaskAsynctask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;
        public InsertTaskAsynctask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            this.taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsynctask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;
        public UpdateTaskAsynctask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            this.taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsynctask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;
        public DeleteTaskAsynctask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            this.taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTaskAsynctask extends AsyncTask<Void,Void,Void>{
        private TaskDao taskDao;
        public DeleteAllTaskAsynctask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.taskDao.deleteAll();
            return null;
        }
    }

}
